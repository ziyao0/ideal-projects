package com.ziyao.ideal.generator.core;


import com.ziyao.ideal.generator.config.CodeGenConfig;
import com.ziyao.ideal.generator.config.StrategyConfig;
import com.ziyao.ideal.generator.config.rules.NamingStrategy;
import com.ziyao.ideal.generator.fill.Column;
import com.ziyao.ideal.generator.mybatisplus.FieldFill;
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
                // controller 相关策略
                .controllerBuilder()
                .superClass(codeGenConfig.getSuperControllerClass())
                // service 相关策略
                .serviceBuilder()

                .formatServiceFileName("%sService")
                .build();

        if (codeGenConfig.isEnableJpa()) {
            builder.controllerBuilder()
                    .serviceBuilder()
                    .superServiceClass(JapService.class)
                    .superServiceImplClass(JapServiceImpl.class)
                    .mapperBuilder()
                    .disable()
                    .disableMapperXml()
                    .disableMapper()
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
    }
}
