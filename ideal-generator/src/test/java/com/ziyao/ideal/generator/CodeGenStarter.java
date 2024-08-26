package com.ziyao.ideal.generator;


import com.ziyao.ideal.generator.config.CodeGenConfig;
import com.ziyao.ideal.generator.config.DataSourceConfig;
import com.ziyao.ideal.generator.core.DataSourceConfigBuilder;
import com.ziyao.ideal.generator.core.GlobalConfigBuilder;
import com.ziyao.ideal.generator.core.PackageConfigBuilder;
import com.ziyao.ideal.generator.core.StrategyConfigBuilder;

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
                .execute();
    }


    private static CodeGenConfig generatorConfig() {
        CodeGenConfig gc = new CodeGenConfig();


        gc.setParent("com.ziyao.ideal");
//        gc.setProjectDir(System.getProperty("user.dir") + "/cloud-modules/ideal-cloud-uaa");
        gc.setProjectDir(System.getProperty("user.dir") + "/ideal-generator");
        gc.setModuleName("code");
        gc.setEnableJpa(true);


        gc.setInclude("application");
        return gc;
    }
}

