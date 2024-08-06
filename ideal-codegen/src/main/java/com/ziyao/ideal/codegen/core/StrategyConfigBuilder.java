package com.ziyao.ideal.codegen.core;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.ziyao.ideal.codegen.CodeGenConst;
import com.ziyao.ideal.codegen.config.CodeGenConfig;
import com.ziyao.ideal.jpa.extension.service.JapService;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class StrategyConfigBuilder {


    public static void strategyConfig(StrategyConfig.Builder builder, CodeGenConfig codeGenConfig) {

        builder.addInclude(codeGenConfig.getInclude().split(","))

                .entityBuilder()
                .enableFileOverride()
                .enableLombok()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                // controller 相关策略
                .controllerBuilder()
                .template("/templates/controllerJpa.java")
                .superClass(codeGenConfig.getSuperControllerClass())
                .enableRestStyle()
                // service 相关策略
                .serviceBuilder()

                .formatServiceFileName("%sService")
                .build();

        if (codeGenConfig.isEnableJpa()) {
            builder.controllerBuilder()
                    .template(CodeGenConst.JPA_CONTROLLER_TEMPLATE)
                    .serviceBuilder()
                    .superServiceClass(JapService.class)
                    .serviceTemplate(CodeGenConst.JPA_SERVICE_TEMPLATE)
                    .serviceImplTemplate(CodeGenConst.JPA_SERVICE_IMPL_TEMPLATE)
                    .superServiceImplClass(JapServiceImpl.class)
                    .mapperBuilder()
                    .disable()
                    .disableMapperXml()
                    .disableMapper()
                    .entityBuilder()
                    .javaTemplate(CodeGenConst.JPA_ENTITY_TEMPLATE);

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
    }
}
