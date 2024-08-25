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
package com.ziyao.ideal.generator.config.po;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.config.ConstVal;
import com.ziyao.ideal.generator.config.DataSourceConfig;
import com.ziyao.ideal.generator.config.GlobalConfig;
import com.ziyao.ideal.generator.config.IKeyWordsHandler;
import com.ziyao.ideal.generator.config.builder.ConfigBuilder;
import com.ziyao.ideal.generator.config.builder.Entity;
import com.ziyao.ideal.generator.config.rules.IColumnType;
import com.ziyao.ideal.generator.config.rules.NamingStrategy;
import com.ziyao.ideal.generator.fill.Column;
import com.ziyao.ideal.generator.fill.Property;
import com.ziyao.ideal.generator.jdbc.DatabaseMetaDataWrapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.JdbcType;

import java.util.Map;

/**
 * 表字段信息
 *
 * @author YangHu
 * @since 2016-12-03
 */
@Getter
public class TableField {

    /**
     * 是否做注解转换
     */
    private boolean convert;

    /**
     * 是否主键
     */
    private boolean idKey;

    /**
     * 主键是否为自增类型
     */
    private boolean autoIncrIdKey;

    /**
     * 字段名称
     */
    private final String name;


    /**
     * 属性名称
     */
    private String propertyName;

    /**
     * 字段类型
     */
    private IColumnType columnType;

    /**
     * 字段注释
     */

    private String comment;

    /**
     * 填充
     */
    private String fill;

    /**
     * 是否关键字
     */
    private boolean keyWords;

    /**
     * 数据库字段（关键字含转义符号）
     */
    private String columnName;

    /**
     * 自定义查询字段列表
     */
    @Setter
    private Map<String, Object> customMap;

    /**
     * 字段元数据信息
     */
    @Setter
    private MetaInfo metaInfo;

    /**
     * 实体属性配置
     */
    private final Entity entity;

    /**
     * 数据库配置
     */
    private final DataSourceConfig dataSourceConfig;

    /**
     * 全局配置
     */
    private final GlobalConfig globalConfig;

    /**
     * 构造方法
     *
     * @param configBuilder 配置构建
     * @param name          数据库字段名称
     */
    public TableField(@NonNull ConfigBuilder configBuilder, @NonNull String name) {
        this.name = name;
        this.columnName = name;
        this.entity = configBuilder.getStrategyConfig().entity();
        this.dataSourceConfig = configBuilder.getDataSourceConfig();
        this.globalConfig = configBuilder.getGlobalConfig();
    }

    /**
     * 设置属性名称
     *
     * @param propertyName 属性名
     * @param columnType   字段类型
     */
    public void setPropertyName(@NonNull String propertyName, @NonNull IColumnType columnType) {
        this.columnType = columnType;
        if (entity.isBooleanColumnRemoveIsPrefix()
                && "boolean".equalsIgnoreCase(this.getPropertyType()) && propertyName.startsWith("is")) {
            this.convert = true;
            this.propertyName = Strings.removePrefixAfterPrefixToLower(propertyName, 2);
            return;
        }
        // 下划线转驼峰策略
        if (NamingStrategy.underline_to_camel.equals(this.entity.getColumnNaming())) {
            this.convert = !propertyName.equalsIgnoreCase(NamingStrategy.underlineToCamel(this.columnName));
        }
        // 原样输出策略
        if (NamingStrategy.no_change.equals(this.entity.getColumnNaming())) {
            this.convert = !propertyName.equalsIgnoreCase(this.columnName);
        }
        if (entity.isTableFieldAnnotationEnable()) {
            this.convert = true;
        } else {
            if (this.idKey) {
                this.convert = !ConstVal.DEFAULT_ID_NAME.equals(propertyName);
            }
        }
        this.propertyName = propertyName;
    }

    public String getPropertyType() {
        if (null != columnType) {
            return columnType.getType();
        }
        return null;
    }

    /**
     * 按 JavaBean 规则来生成 get 和 set 方法后面的属性名称
     * 需要处理一下特殊情况：
     * <p>
     * 1、如果只有一位，转换为大写形式
     * 2、如果多于 1 位，只有在第二位是小写的情况下，才会把第一位转为小写
     * <p>
     * 我们并不建议在数据库对应的对象中使用基本类型，因此这里不会考虑基本类型的情况
     */
    public String getCapitalName() {
        if (propertyName.length() == 1) {
            return propertyName.toUpperCase();
        }
        if (Character.isLowerCase(propertyName.charAt(1))) {
            return Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1);
        }
        return propertyName;
    }

    /**
     * 获取注解字段名称
     *
     * @return 字段
     */
    public String getAnnotationColumnName() {
        if (keyWords) {
            if (columnName.startsWith("\"")) {
                return String.format("\\\"%s\\\"", name);
            }
        }
        return columnName;
    }

    /**
     * 是否为乐观锁字段
     *
     * @return 是否为乐观锁字段
     */
    public boolean isVersionField() {
        String propertyName = entity.getVersionPropertyName();
        String columnName = entity.getVersionColumnName();
        return Strings.hasLength(propertyName) && this.propertyName.equals(propertyName)
                || Strings.hasLength(columnName) && this.name.equalsIgnoreCase(columnName);
    }

    /**
     * 是否为逻辑删除字段
     *
     * @return 是否为逻辑删除字段
     */
    public boolean isLogicDeleteField() {
        String propertyName = entity.getLogicDeletePropertyName();
        String columnName = entity.getLogicDeleteColumnName();
        return Strings.hasLength(propertyName) && this.propertyName.equals(propertyName)
                || Strings.hasLength(columnName) && this.name.equalsIgnoreCase(columnName);
    }

    /**
     * 设置主键
     *
     * @param autoIncrement 自增标识
     */
    public void primaryKey(boolean autoIncrement) {
        this.idKey = true;
        this.autoIncrIdKey = autoIncrement;
    }

    public TableField setComment(String comment) {
        this.comment = this.globalConfig.isSwagger()
                && Strings.hasLength(comment) ? comment.replace("\"", "\\\"") : comment;
        return this;
    }

    public TableField setColumnName(String columnName) {
        this.columnName = columnName;
        IKeyWordsHandler keyWordsHandler = dataSourceConfig.getKeyWordsHandler();
        if (keyWordsHandler != null && keyWordsHandler.isKeyWords(columnName)) {
            this.keyWords = true;
            this.columnName = keyWordsHandler.formatColumn(columnName);
        }
        return this;
    }

    public String getFill() {
        if (Strings.isEmpty(fill)) {
            entity.getTableFillList().stream()
                    //忽略大写字段问题
                    .filter(tf -> tf instanceof Column && tf.getName().equalsIgnoreCase(name)
                            || tf instanceof Property && tf.getName().equals(propertyName))
                    .findFirst().ifPresent(tf -> this.fill = tf.getFieldFill().name());
        }
        return fill;
    }

    /**
     * 元数据信息
     * <p>
     * 2021/2/8
     */
    @Getter
    public static class MetaInfo {

        /**
         * 表名称
         */
        private String tableName;

        /**
         * 字段名称
         */
        private String columnName;

        /**
         * 字段长度
         */
        private int length;

        /**
         * 是否非空
         */
        private boolean nullable;

        /**
         * 字段注释
         */

        private String remarks;

        /**
         * 字段默认值
         */

        private String defaultValue;

        /**
         * 字段精度
         */

        private int scale;

        /**
         * JDBC类型
         */

        private JdbcType jdbcType;

        /**
         * 类型名称(可用做额外判断处理,例如在pg下,json,uuid,jsonb,tsquery这种都认为是OHTER 1111)
         *
         * 
         */

        private String typeName;

        /**
         * 是否为生成列
         *
         * @since 3.5.8
         */
        private boolean generatedColumn;

        public MetaInfo(DatabaseMetaDataWrapper.Column column, TableInfo tableInfo) {
            if (column != null) {
                this.tableName = tableInfo.getName();
                this.columnName = column.getName();
                this.length = column.getLength();
                this.nullable = column.isNullable();
                this.remarks = column.getRemarks();
                this.defaultValue = column.getDefaultValue();
                this.scale = column.getScale();
                this.jdbcType = column.getJdbcType();
                this.typeName = column.getTypeName();
                this.generatedColumn = column.isGeneratedColumn();
            }
        }

        @Override
        public String toString() {
            return "MetaInfo{" +
                    "tableName='" + tableName + '\'' +
                    ", columnName='" + columnName + '\'' +
                    ", length=" + length +
                    ", nullable=" + nullable +
                    ", remarks='" + remarks + '\'' +
                    ", defaultValue='" + defaultValue + '\'' +
                    ", scale=" + scale +
                    ", jdbcType=" + jdbcType +
                    ", typeName='" + typeName + '\'' +
                    ", generatedColumn=" + generatedColumn +
                    '}';
        }
    }
}
