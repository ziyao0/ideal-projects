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


        gc.setUrl("jdbc:mysql://123.249.75.59:33066/code_harbor"); //?useUnicode=true&useSSL=false&characterEncoding=utf8
        gc.setUserName("root");
        gc.setPassword("Zzy@1996");

        gc.setParent("com.ziyao.ideal");
        gc.setProjectDir(System.getProperty("user.dir") + "/cloud-modules/ideal-cloud-uaa");
        gc.setModuleName("uaa");
        gc.setEnableJpa(true);

        gc.setInclude("application,authorization,authorization_record,department,global_login_restriction,login_config,login_record,menu,restriction_rule,role,role_menu,user,user_login_restriction,user_role");

        return gc;
    }
}
