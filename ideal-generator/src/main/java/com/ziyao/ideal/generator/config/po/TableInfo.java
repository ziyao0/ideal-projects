package com.ziyao.ideal.generator.config.po;


import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.config.GlobalConfig;
import com.ziyao.ideal.generator.config.StrategyConfig;
import com.ziyao.ideal.generator.config.builder.ConfigBuilder;
import com.ziyao.ideal.generator.config.builder.Entity;
import com.ziyao.ideal.generator.config.rules.IColumnType;
import com.ziyao.ideal.generator.mybatisplus.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 表信息，关联到当前字段信息
 */
@Getter
public class TableInfo {

    /**
     * 策略配置
     */
    private final StrategyConfig strategyConfig;

    /**
     * 全局配置信息
     */
    private final GlobalConfig globalConfig;

    /**
     * 包导入信息
     */
    private final Set<String> importPackages = new TreeSet<>();

    /**
     * 是否转换
     */
    private boolean convert;

    /**
     * 表名称
     */
    private final String name;

    /**
     * 表注释
     */
    private String comment;

    /**
     * 实体名称
     */
    private String entityName;
    /**
     * dto
     */
    private String dtoName;
    /**
     * mapper名称
     */
    private String mapperName;
    /**
     * mapper名称
     */
    private String repositoryName;

    /**
     * xml名称
     */
    private String xmlName;

    /**
     * service名称
     */
    private String serviceName;

    /**
     * serviceImpl名称
     */
    private String serviceImplName;

    /**
     * controller名称
     */
    private String controllerName;

    /**
     * 表字段
     */
    private final List<TableField> fields = new ArrayList<>();

    /**
     * 是否有主键
     */
    private boolean havePrimaryKey;

    /**
     * 公共字段
     */
    private final List<TableField> commonFields = new ArrayList<>();

    /**
     * 字段名称集
     */
    private String fieldNames;

    /**
     * 实体
     */
    private final Entity entity;
    /**
     * 主键类型
     */
    private String idPropertyType;

    /**
     * 构造方法
     *
     * @param configBuilder 配置构建
     * @param name          表名
     */
    public TableInfo(@NonNull ConfigBuilder configBuilder, @NonNull String name) {
        this.strategyConfig = configBuilder.getStrategyConfig();
        this.globalConfig = configBuilder.getGlobalConfig();
        this.entity = configBuilder.getStrategyConfig().entity();
        this.name = name;
    }

    /**
     *
     */
    protected TableInfo setConvert() {
        if (strategyConfig.startsWithTablePrefix(name) || entity.isTableFieldAnnotationEnable()) {
            this.convert = true;
        } else {
            this.convert = !entityName.equalsIgnoreCase(name);
        }
        return this;
    }

    public String getEntityPath() {
        return entityName.substring(0, 1).toLowerCase() + entityName.substring(1);
    }

    /**
     * @param entityName 实体名称
     * @return this
     */
    public TableInfo setEntityName(@NonNull String entityName) {
        this.entityName = entityName;
        setConvert();
        return this;
    }

    /**
     * 添加字段
     *
     * @param field 字段
     */
    public void addField(@NonNull TableField field) {
        if (entity.matchIgnoreColumns(field.getColumnName())) {
            // 忽略字段不在处理
            return;
        } else if (entity.matchSuperEntityColumns(field.getColumnName())) {
            this.commonFields.add(field);
        } else {
            this.fields.add(field);
        }
        if (field.isIdKey()) {
            idPropertyType = field.getPropertyType();
        }
    }

    /**
     * @param pkgs 包空间
     * @return this
     */
    public TableInfo addImportPackages(@NonNull String... pkgs) {
        return addImportPackages(Arrays.asList(pkgs));
    }

    public TableInfo addImportPackages(@NonNull List<String> pkgList) {
        importPackages.addAll(pkgList);
        return this;
    }

    /**
     * 转换filed实体为 xml mapper 中的 base column 字符串信息
     */
    public String getFieldNames() {
        if (Strings.isEmpty(fieldNames)) {
            this.fieldNames = this.fields.stream().map(TableField::getColumnName).collect(Collectors.joining(", "));
        }
        return this.fieldNames;
    }

    /**
     * 导包处理
     */
    public void importPackage() {
        String superEntity = entity.getSuperClass();
        if (Strings.hasLength(superEntity)) {
            // 自定义父类
            this.importPackages.add(superEntity);
        }
        if (entity.isSerialVersionUID() || entity.isActiveRecord()) {
            this.importPackages.add(Serializable.class.getCanonicalName());
        }
        if (this.isConvert()) {
            this.importPackages.add(TableName.class.getCanonicalName());
        }
        IdType idType = entity.getIdType();
        if (null != idType && this.isHavePrimaryKey()) {
            // 指定需要 IdType 场景
            this.importPackages.add(IdType.class.getCanonicalName());
            this.importPackages.add(TableId.class.getCanonicalName());
        }
        this.fields.forEach(field -> {
            IColumnType columnType = field.getColumnType();
            if (null != columnType && null != columnType.getPkg()) {
                importPackages.add(columnType.getPkg());
            }
            if (field.isIdKey()) {
                // 主键
                if (field.isConvert() || field.isAutoIncrIdKey()) {
                    importPackages.add(TableId.class.getCanonicalName());
                }
                // 自增
                if (field.isAutoIncrIdKey()) {
                    importPackages.add(IdType.class.getCanonicalName());
                }
            } else if (field.isConvert()) {
                // 普通字段
                importPackages.add(TableField.class.getCanonicalName());
            }
            if (null != field.getFill()) {
                // 填充字段
                importPackages.add(TableField.class.getCanonicalName());
                //TODO 好像default的不用处理也行,这个做优化项目.
                importPackages.add(FieldFill.class.getCanonicalName());
            }
            if (field.isVersionField()) {
                this.importPackages.add(Version.class.getCanonicalName());
            }
            if (field.isLogicDeleteField()) {
                this.importPackages.add(TableLogic.class.getCanonicalName());
            }
        });
    }

    /**
     * 处理表信息(文件名与导包)
     */
    public void processTable() {
        String entityName = entity.getNameConvert().entityNameConvert(this);
        this.entityName = entity.getNameConvertor().convert(entityName);
        this.dtoName = entity.getConverterDTOFileName().convert(entityName);
        this.mapperName = strategyConfig.mapper().getConverterMapperFileName().convert(entityName);
        this.xmlName = strategyConfig.mapper().getConverterXmlFileName().convert(entityName);
        this.serviceName = strategyConfig.service().getConverterServiceFileName().convert(entityName);
        this.serviceImplName = strategyConfig.service().getConverterServiceImplFileName().convert(entityName);
        this.controllerName = strategyConfig.controller().getNameConvertor().convert(entityName);
        this.repositoryName = strategyConfig.repository().getNameConvertor().convert(entityName);
        this.importPackage();
    }

    public TableInfo setComment(String comment) {
        this.comment = this.globalConfig.isSwagger()
                && Strings.hasLength(comment) ? comment.replace("\"", "\\\"") : comment;
        return this;
    }

    public TableInfo setHavePrimaryKey(boolean havePrimaryKey) {
        this.havePrimaryKey = havePrimaryKey;
        return this;
    }

    @NonNull
    public Set<String> getImportPackages() {
        return importPackages;
    }

    public TableInfo setConvert(boolean convert) {
        this.convert = convert;
        return this;
    }

    @NonNull
    public List<TableField> getFields() {
        return fields;
    }

    @NonNull
    public List<TableField> getCommonFields() {
        return commonFields;
    }
}
