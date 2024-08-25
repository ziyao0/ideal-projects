/*
 * Copyright (c) 2011-2024, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ziyao.ideal.generator.query;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.config.DataSourceConfig;
import com.ziyao.ideal.generator.config.builder.ConfigBuilder;
import com.ziyao.ideal.generator.config.builder.Entity;
import com.ziyao.ideal.generator.config.po.TableField;
import com.ziyao.ideal.generator.config.po.TableInfo;
import com.ziyao.ideal.generator.config.rules.IColumnType;
import com.ziyao.ideal.generator.jdbc.DatabaseMetaDataWrapper;
import com.ziyao.ideal.generator.type.ITypeConvertHandler;
import com.ziyao.ideal.generator.type.TypeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 元数据查询数据库信息.
 * <p>
 * 2022/5/11.
 *
 * @see ITypeConvertHandler 类型转换器(如果默认逻辑不能满足，可实现此接口重写类型转换)
 * <p>
 * 测试通过的数据库：H2、Mysql-5.7.37、Mysql-8.0.25、PostgreSQL-11.15、PostgreSQL-14.1、Oracle-11.2.0.1.0、DM8
 * </p>
 * <p>
 * FAQ:
 * 1.Mysql无法读取表注释: 链接增加属性 remarks=true&useInformationSchema=true 或者通过{@link DataSourceConfig.Builder#addConnectionProperty(String, String)}设置
 * 2.Oracle无法读取注释: 增加属性remarks=true，也有些驱动版本说是增加remarksReporting=true {@link DataSourceConfig.Builder#addConnectionProperty(String, String)}
 * </p>
 * 
 */
public class DefaultQuery extends AbstractDatabaseQuery {

    private final TypeRegistry typeRegistry;
    protected final DatabaseMetaDataWrapper databaseMetaDataWrapper;

    public DefaultQuery(@NonNull ConfigBuilder configBuilder) {
        super(configBuilder);
        typeRegistry = new TypeRegistry(configBuilder.getGlobalConfig());
        this.databaseMetaDataWrapper = new DatabaseMetaDataWrapper(dataSourceConfig.getConn(), dataSourceConfig.getSchemaName());
    }

    @Override
    public @NonNull List<TableInfo> queryTables() {
        try {
            boolean isInclude = !strategyConfig.getInclude().isEmpty();
            boolean isExclude = !strategyConfig.getExclude().isEmpty();
            //所有的表信息
            List<TableInfo> tableList = new ArrayList<>();
            List<DatabaseMetaDataWrapper.Table> tables = this.getTables();
            //需要反向生成或排除的表信息
            List<TableInfo> includeTableList = new ArrayList<>();
            List<TableInfo> excludeTableList = new ArrayList<>();
            tables.forEach(table -> {
                String tableName = table.getName();
                if (Strings.hasLength(tableName)) {
                    TableInfo tableInfo = new TableInfo(this.configBuilder, tableName);
                    tableInfo.setComment(table.getRemarks());
                    if (isInclude && strategyConfig.matchIncludeTable(tableName)) {
                        includeTableList.add(tableInfo);
                    } else if (isExclude && strategyConfig.matchExcludeTable(tableName)) {
                        excludeTableList.add(tableInfo);
                    }
                    tableList.add(tableInfo);
                }
            });
            filter(tableList, includeTableList, excludeTableList);
            // 性能优化，只处理需执行表字段 https://github.com/baomidou/mybatis-plus/issues/219
            tableList.forEach(this::convertTableFields);
            return tableList;
        } finally {
            // 数据库操作完成,释放连接对象
            databaseMetaDataWrapper.closeConnection();
        }
    }

    protected List<DatabaseMetaDataWrapper.Table> getTables() {
        // 是否跳过视图
        boolean skipView = strategyConfig.isSkipView();
        // 获取表过滤
        String tableNamePattern = null;
        if (strategyConfig.getLikeTable() != null) {
            tableNamePattern = strategyConfig.getLikeTable().getValue();
        }
        return databaseMetaDataWrapper.getTables(tableNamePattern, skipView ? new String[]{"TABLE"} : new String[]{"TABLE", "VIEW"});
    }

    protected void convertTableFields(@NonNull TableInfo tableInfo) {
        String tableName = tableInfo.getName();
        Map<String, DatabaseMetaDataWrapper.Column> columnsInfoMap = getColumnsInfo(tableName);
        Entity entity = strategyConfig.entity();
        columnsInfoMap.forEach((k, columnInfo) -> {
            String columnName = columnInfo.getName();
            TableField field = new TableField(this.configBuilder, columnName);
            // 处理ID
            if (columnInfo.isPrimaryKey()) {
                field.primaryKey(columnInfo.isAutoIncrement());
                tableInfo.setHavePrimaryKey(true);
                if (field.isAutoIncrIdKey() && entity.getIdType() != null) {
                    LOGGER.warn("当前表[{}]的主键为自增主键，会导致全局主键的ID类型设置失效!", tableName);
                }
            }
            field.setColumnName(columnName).setComment(columnInfo.getRemarks());
            String propertyName = entity.getNameConvert().propertyNameConvert(field);
            // 设置字段的元数据信息
            TableField.MetaInfo metaInfo = new TableField.MetaInfo(columnInfo, tableInfo);
            IColumnType columnType;
            ITypeConvertHandler typeConvertHandler = dataSourceConfig.getTypeConvertHandler();
            if (typeConvertHandler != null) {
                columnType = typeConvertHandler.convert(globalConfig, typeRegistry, metaInfo);
            } else {
                columnType = typeRegistry.getColumnType(metaInfo);
            }
            field.setPropertyName(propertyName, columnType);
            field.setMetaInfo(metaInfo);
            tableInfo.addField(field);
        });
        tableInfo.processTable();
    }

    protected Map<String, DatabaseMetaDataWrapper.Column> getColumnsInfo(String tableName) {
        return databaseMetaDataWrapper.getColumnsInfo(tableName, true);
    }
}