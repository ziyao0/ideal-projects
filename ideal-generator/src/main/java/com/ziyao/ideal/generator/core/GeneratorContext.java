package com.ziyao.ideal.generator.core;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.ConfigSettings;
import com.ziyao.ideal.generator.core.meta.Column;
import com.ziyao.ideal.generator.core.meta.Field;
import com.ziyao.ideal.generator.settings.GlobalSettings;
import com.ziyao.ideal.generator.settings.StrategySettings;
import com.ziyao.ideal.generator.template.PersistentTemplate;
import com.ziyao.ideal.generator.utils.PackageUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class GeneratorContext {

    private final StrategySettings strategy;
    private final GlobalSettings globalSettings;
    private final Set<String> baseImportPackages = new HashSet<>();
    private final Set<String> importPackages = new HashSet<>();
    private final String tableName;
    @Setter
    private String comment;
    private String entityName;
    private String dtoName;
    private String repositoryName;
    private String mapperName;
    private String serviceName;
    private String serviceImplName;
    private String controllerName;
    private final List<Field> fields = new ArrayList<>();
    private final List<Field> commonFields = new ArrayList<>();
    private Field primary;
    private String fieldNames;
    private String fieldNamesForBr;

    public GeneratorContext(ConfigSettings configSettings, String tableName) {
        this.strategy = configSettings.getStrategySettings();
        this.globalSettings = configSettings.getGlobalSettings();
        this.tableName = tableName;
    }


    public void addField(@NonNull Column column) {
        String name = column.getName();
        DataType dataType = column.getDataType();
        // 添加导包配置
        if (Strings.hasText(dataType.getPkg())) {
            baseImportPackages.add(dataType.getPkg());
        }
        String comment = column.getComment();
        String propertyName = NamingStrategy.underlineToCamel(name);
        Field field = Field.of(name, propertyName, dataType.getJavaType());
        field.setComment(comment);
        field.setPrimary(column.isPrimary());
        // 如果是主键，则向主键赋值
        if (column.isPrimary()) {
            primary = field;
        }
        fields.add(field);
    }

    public String getFieldNames() {
        if (Strings.isEmpty(fieldNames)) {
            List<String> fields = this.fields.stream()
                    .sorted(Comparator.comparing(Field::isPrimary).reversed())
                    .map(Field::getName).collect(Collectors.toList());
            this.fieldNames = Strings.collectionToCommaDelimitedString(fields);
        }
        return fieldNames;
    }

    public String getFieldNamesForBr() {
        if (Strings.isEmpty(fieldNamesForBr)) {
            List<String> fields = this.fields.stream()
                    .sorted(Comparator.comparing(Field::isPrimary).reversed())
                    .map(Field::getName).collect(Collectors.toList());
            this.fieldNamesForBr = Strings.collectionToDelimitedString(fields, ",\n        ");
        }
        return fieldNamesForBr;
    }

    public void addImportPackages(@NonNull String... packages) {
        addImportPackages(Arrays.asList(packages));
    }

    public void addImportPackages(@NonNull List<String> pkgList) {
        importPackages.addAll(pkgList);
    }

    public void process() {
        PersistentTemplate persistent = strategy.persistent();
        String entityName = persistent.getEntityName(tableName);
        this.entityName = persistent.getConvertor().convert(entityName);
        this.dtoName = strategy.persistent().getDtoConvertor().convert(entityName);
        this.serviceName = strategy.service().getConvertor().convert(entityName);
        this.serviceImplName = strategy.service().getServiceImplConvertor().convert(entityName);
        this.controllerName = strategy.controller().getConvertor().convert(entityName);
        this.repositoryName = strategy.repository().getConvertor().convert(entityName);
        this.mapperName = Naming.Mapper.getConverter().convert(entityName);

        PersistType persistType = globalSettings.getPersistType();
        switch (persistType) {
            case jpa -> {
                addImportPackages(PackageUtils.ID_PKG, PackageUtils.ENTITY_PKG);
            }
            case tk_mybatis -> {
                addImportPackages(PackageUtils.TABLE_PKG,
                        PackageUtils.ID_PKG,
                        PackageUtils.COLUMN_PKG);
            }
            case mybatis_plus -> {
                addImportPackages(
                        PackageUtils.MYBATIS_PLUS_TABLE_NAME_PKG,
                        PackageUtils.MYBATIS_PLUS_TABLE_ID_PKG,
                        PackageUtils.MYBATIS_PLUS_TABLE_FIELD_PKG
                );
            }
        }
    }
}
