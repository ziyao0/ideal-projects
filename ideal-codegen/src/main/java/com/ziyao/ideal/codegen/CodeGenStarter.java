package com.ziyao.ideal.codegen;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.ziyao.ideal.codegen.config.CodeGenConfig;
import com.ziyao.ideal.codegen.core.*;

/**
 * @author zhangziyao
 */
public class CodeGenStarter {

    public static void main(String[] args) {

        CodeGenConfig config = generatorConfig();
        // 代码生成器
        FastAutoGenerator.create(
                        new DataSourceConfig.Builder(config.getUrl(), config.getUserName(), config.getPassword()))
                // 全局配置
                .globalConfig(builder -> GlobalConfigBuilder.globalConfig(builder, config))
                // 数据库配置
                .dataSourceConfig(builder -> DataSourceConfigBuilder.datasourceConfig(builder, config))
                // 包配置
                .packageConfig(builder -> PackageConfigBuilder.packageConfig(builder, config))
                // 策略配置
                .strategyConfig(builder -> StrategyConfigBuilder.strategyConfig(builder, config))
                // 自定义配置 可以生成自定义文件
                .injectionConfig(builder -> InjectionConfigBuilder.injectionConfig(builder, config))
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }


    private static CodeGenConfig generatorConfig() {
        CodeGenConfig gc = new CodeGenConfig();


        gc.setUrl("jdbc:mysql://:33066/code_harbor"); //?useUnicode=true&useSSL=false&characterEncoding=utf8
        gc.setUserName("");
        gc.setPassword("");

        gc.setModuleName("config");
        gc.setEnableJpa(true);
        gc.setParent("com.ziyao.ideal");

//        gc.setSuperEntityClass("com.harbor.web.orm.BaseEntity");

//        gc.setSuperEntityColumns("id,CREATED_BY,CREATED_AT,MODIFIED_BY,MODIFIED_AT");

        gc.setSuperControllerClass("com.ziyao.ideal.web.base.JpaBaseController");

//        gc.setInclude("authorization,user,role,user_role,application,department,menu,role_menu");
        gc.setInclude("config_item,config");
        gc.setProjectDir(System.getProperty("user.dir") + "/cloud-modules/ideal-cloud-config");


        return gc;
    }
}
