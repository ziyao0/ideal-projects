package com.ziyao.ideal.generator.query;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.config.IDbQuery;
import com.ziyao.ideal.generator.config.ITypeConvert;
import com.ziyao.ideal.generator.config.builder.ConfigBuilder;
import com.ziyao.ideal.generator.config.builder.Entity;
import com.ziyao.ideal.generator.config.po.TableField;
import com.ziyao.ideal.generator.config.po.TableInfo;
import com.ziyao.ideal.generator.config.querys.DbQueryDecorator;
import com.ziyao.ideal.generator.config.rules.IColumnType;
import com.ziyao.ideal.generator.jdbc.DatabaseMetaDataWrapper;

import java.sql.SQLException;
import java.util.*;

/**
 * 这是兼容以前旧版本提供的查询方式，需要每个数据库对接适配。
 *
 * @see IDbQuery 数据库适配
 * @see ITypeConvert 类型适配处理
 */
public class SQLQuery extends AbstractDatabaseQuery {

    protected final DbQueryDecorator dbQuery;
    protected final DatabaseMetaDataWrapper databaseMetaDataWrapper;

    public SQLQuery(@NonNull ConfigBuilder configBuilder) {
        super(configBuilder);
        this.dbQuery = new DbQueryDecorator(dataSourceConfig, strategyConfig);
        this.databaseMetaDataWrapper = new DatabaseMetaDataWrapper(dbQuery.getConnection(), dataSourceConfig.getSchemaName());
    }

    @NonNull
    @Override
    public List<TableInfo> queryTables() {
        boolean isInclude = !strategyConfig.getInclude().isEmpty();
        boolean isExclude = !strategyConfig.getExclude().isEmpty();
        //所有的表信息
        List<TableInfo> tableList = new ArrayList<>();

        //需要反向生成或排除的表信息
        List<TableInfo> includeTableList = new ArrayList<>();
        List<TableInfo> excludeTableList = new ArrayList<>();
        try {
            dbQuery.execute(dbQuery.tablesSql(), result -> {
                String tableName = result.getStringResult(dbQuery.tableName());
                if (Strings.hasLength(tableName)) {
                    TableInfo tableInfo = new TableInfo(this.configBuilder, tableName);
                    String tableComment = result.getTableComment();
                    // 跳过视图
                    if (!(strategyConfig.isSkipView() && tableComment.toUpperCase().contains("VIEW"))) {
                        tableInfo.setComment(tableComment);
                        if (isInclude && strategyConfig.matchIncludeTable(tableName)) {
                            includeTableList.add(tableInfo);
                        } else if (isExclude && strategyConfig.matchExcludeTable(tableName)) {
                            excludeTableList.add(tableInfo);
                        }
                        tableList.add(tableInfo);
                    }
                }
            });
            filter(tableList, includeTableList, excludeTableList);
            // 性能优化，只处理需执行表字段 https://github.com/baomidou/mybatis-plus/issues/219
            tableList.forEach(this::convertTableFields);
            return tableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 数据库操作完成,释放连接对象
            dbQuery.closeConnection();
        }
    }

    protected void convertTableFields(@NonNull TableInfo tableInfo) {
        String tableName = tableInfo.getName();
        try {
            Map<String, DatabaseMetaDataWrapper.Column> columnsInfoMap = databaseMetaDataWrapper.getColumnsInfo(tableName, false);
            String tableFieldsSql = dbQuery.tableFieldsSql(tableName);
            Set<String> pkColumns = new HashSet<>();
            String primaryKeySql = dbQuery.primaryKeySql(dataSourceConfig, tableName);
            boolean selectPk = Strings.hasLength(primaryKeySql);
            if (selectPk) {
                dbQuery.execute(primaryKeySql, result -> {
                    String primaryKey = result.getStringResult(dbQuery.fieldKey());
                    if (Boolean.parseBoolean(primaryKey)) {
                        pkColumns.add(result.getStringResult(dbQuery.fieldName()));
                    }
                });
            }
            Entity entity = strategyConfig.entity();
            dbQuery.execute(tableFieldsSql, result -> {
                String columnName = result.getStringResult(dbQuery.fieldName());
                TableField field = new TableField(this.configBuilder, columnName);
                DatabaseMetaDataWrapper.Column columnInfo = columnsInfoMap.get(columnName.toLowerCase());
                // 设置字段的元数据信息
                TableField.MetaInfo metaInfo = new TableField.MetaInfo(columnInfo, tableInfo);
                // 避免多重主键设置，目前只取第一个找到ID，并放到list中的索引为0的位置
                boolean isId = selectPk ? pkColumns.contains(columnName) : result.isPrimaryKey();
                // 处理ID
                if (isId) {
                    field.primaryKey(dbQuery.isKeyIdentity(result.getResultSet()));
                    tableInfo.setHavePrimaryKey(true);
                    if (field.isAutoIncrIdKey() && entity.getIdType() != null) {
                        LOGGER.warn("当前表[{}]的主键为自增主键，会导致全局主键的ID类型设置失效!", tableName);
                    }
                }
                // 处理ID
                field.setColumnName(columnName)
                        .setComment(result.getFiledComment())
                        .setCustomMap(dbQuery.getCustomFields(result.getResultSet()));
                String propertyName = entity.getNameConvert().propertyNameConvert(field);
                IColumnType columnType = dataSourceConfig.getTypeConvert().processTypeConvert(globalConfig, field);
                field.setPropertyName(propertyName, columnType);
                field.setMetaInfo(metaInfo);
                tableInfo.addField(field);
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tableInfo.processTable();
    }
}
