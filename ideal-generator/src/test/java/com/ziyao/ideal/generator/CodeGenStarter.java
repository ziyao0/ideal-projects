package com.ziyao.ideal.generator;


import com.ziyao.ideal.generator.config.DataSourceConfig;
import com.ziyao.ideal.generator.config.rules.DateType;
import com.ziyao.ideal.generator.fill.Column;
import com.ziyao.ideal.generator.mybatisplus.FieldFill;
import com.ziyao.ideal.generator.util.PropertyKeyUtils;
import com.ziyao.ideal.jpa.extension.service.JapService;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author zhangziyao
 */
public class CodeGenStarter {

    public static void main(String[] args) throws IOException {

        Resource resource = new ClassPathResource("generator.properties");

        Properties properties = PropertiesLoaderUtils.loadProperties(resource);

        // 代码生成器
        FastAutoGenerator.create(
                        new DataSourceConfig.Builder(
                                properties.getProperty(PropertyKeyUtils.DB_URL),
                                properties.getProperty(PropertyKeyUtils.DB_USERNAME),
                                properties.getProperty(PropertyKeyUtils.DB_PASSWORD)))
                // 全局配置
                .globalConfig(builder -> {
                    builder.outputDir(System.getProperty("user.dir") + "/ideal-generator" + "/src/main/java")
                            .dateType(DateType.TIME_PACK)
                            .enableJpa()
                            .disableOpenDir();
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.moduleName("code")// 设置父包模块名
                            .parent("com.ziyao.ideal");
                })
                // 策略配置
                .strategyConfig(builder -> {

                    builder.addInclude("application".split(","))
                            .entityBuilder()
                            .enableFileOverride()
                            .enableLombok()
                            // controller 相关策略
                            .controllerBuilder()
                            // service 相关策略
                            .serviceBuilder()
                            .build();
                    boolean isEnableJpa = true;
                    if (isEnableJpa) {
                        builder.serviceBuilder()
                                .superServiceClass(JapService.class)
                                .superServiceImplClass(JapServiceImpl.class)
                                .entityBuilder();

                    } else {
                        builder.entityBuilder()
                                .addTableFills(new Column("CREATED_BY", FieldFill.INSERT))
                                .addTableFills(new Column("CREATED_AT", FieldFill.INSERT))
                                .addTableFills(new Column("MODIFIED_BY", FieldFill.UPDATE))
                                .addTableFills(new Column("MODIFIED_AT", FieldFill.UPDATE))
                                .enableTableFieldAnnotation()
                                .logicDeleteColumnName("deleted")
                                //逻辑删除字段
                                .logicDeleteColumnName("DELETED");
                    }
                })
                .execute();
    }
}

