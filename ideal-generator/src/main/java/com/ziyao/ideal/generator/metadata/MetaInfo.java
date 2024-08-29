package com.ziyao.ideal.generator.metadata;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.ConfigManager;
import com.ziyao.ideal.generator.DataType;
import com.ziyao.ideal.generator.config.GlobalConfig;
import com.ziyao.ideal.generator.config.rules.NamingStrategy;
import com.ziyao.ideal.generator.template.TemplateStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class MetaInfo {

    private final TemplateStrategy strategy;
    private final GlobalConfig globalConfig;
    private final Set<String> importPackages = new HashSet<>();
    private final String tableName;
    @Setter
    private String comment;
    private String entityName;
    private String dtoName;
    private String repositoryName;
    private String serviceName;
    private String serviceImplName;
    private String controllerName;
    private final List<Field> fields = new ArrayList<>();
    private final List<Field> commonFields = new ArrayList<>();
    private Field primaryKey;

    public MetaInfo(ConfigManager configManager, String tableName) {
        this.strategy = configManager.getStrategy();
        this.globalConfig = null;
        this.tableName = tableName;
    }


    public void addField(@NonNull Column column) {
        String name = column.getName();
        DataType dataType = column.getDataType();
        // 添加导包配置
        if (Strings.hasText(dataType.getPkg())) {
            importPackages.add(dataType.getPkg());
        }
        String comment = column.getComment();
        String propertyName = NamingStrategy.underlineToCamel(name);
        Field field = Field.of(name, propertyName, dataType.getJavaType());
        field.setComment(comment);
        // 如果是主键，则向主键赋值
        if (column.isPrimary()) {
            primaryKey = field;
        }
        fields.add(field);
    }

    public MetaInfo addImportPackages(@NonNull String... packages) {
        return addImportPackages(Arrays.asList(packages));
    }

    public MetaInfo addImportPackages(@NonNull List<String> pkgList) {
        importPackages.addAll(pkgList);
        return this;
    }

    public void process() {
        String entityName = NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(tableName));
        this.entityName = strategy.persistent().getConvertor().convert(entityName);
        this.dtoName = strategy.persistent().getDtoConvertor().convert(entityName);
        this.serviceName = strategy.service().getConvertor().convert(entityName);
        this.serviceImplName = strategy.service().getServiceImplConvertor().convert(entityName);
        this.controllerName = strategy.controller().getConvertor().convert(entityName);
        this.repositoryName = strategy.repository().getConvertor().convert(entityName);
    }
}
