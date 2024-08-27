package com.ziyao.ideal.generator.config.builder;

import com.ziyao.ideal.generator.config.GlobalConfig;
import com.ziyao.ideal.generator.config.InjectionConfig;
import com.ziyao.ideal.generator.config.PackageConfig;
import com.ziyao.ideal.generator.config.StrategyConfig;

/**
 * 生成器 Builder
 */
public class GeneratorBuilder {

    /**
     * 全局配置
     *
     * @return GlobalConfig
     */
    public static GlobalConfig globalConfig() {
        return new GlobalConfig.Builder().build();
    }

    /**
     * 全局配置 Builder
     *
     * @return GlobalConfig.Builder
     */
    public static GlobalConfig.Builder globalConfigBuilder() {
        return new GlobalConfig.Builder();
    }

    /**
     * 包相关的配置项
     *
     * @return PackageConfig
     */
    public static PackageConfig packageConfig() {
        return new PackageConfig.Builder().build();
    }

    /**
     * 包相关的配置项 Builder
     *
     * @return PackageConfig.Builder
     */
    public static PackageConfig.Builder packageConfigBuilder() {
        return new PackageConfig.Builder();
    }

    /**
     * 策略配置项
     *
     * @return StrategyConfig
     */
    public static StrategyConfig strategyConfig() {
        return new StrategyConfig.Builder().build();
    }

    /**
     * 策略配置项 Builder
     *
     * @return StrategyConfig.Builder
     */
    public static StrategyConfig.Builder strategyConfigBuilder() {
        return new StrategyConfig.Builder();
    }

    /**
     * 注入配置项
     *
     * @return InjectionConfig
     */
    public static InjectionConfig injectionConfig() {
        return new InjectionConfig.Builder().build();
    }

    /**
     * 注入配置项 Builder
     *
     * @return InjectionConfig.Builder
     */
    public static InjectionConfig.Builder injectionConfigBuilder() {
        return new InjectionConfig.Builder();
    }
}
