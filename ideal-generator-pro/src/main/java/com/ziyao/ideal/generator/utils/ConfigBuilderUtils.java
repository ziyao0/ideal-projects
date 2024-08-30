package com.ziyao.ideal.generator.utils;

import com.ziyao.ideal.generator.properties.GlobalProperties;
import com.ziyao.ideal.generator.properties.PackageProperties;
import com.ziyao.ideal.generator.properties.StrategyProperties;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public abstract class ConfigBuilderUtils {

    /**
     * 全局配置
     *
     * @return GlobalProperties
     */
    public static GlobalProperties globalProperties() {
        return new GlobalProperties.Builder().build();
    }

    /**
     * 包相关的配置项
     *
     * @return PackageProperties
     */
    public static PackageProperties packageProperties() {
        return new PackageProperties.Builder().build();
    }

    /**
     * 策略配置项
     *
     * @return StrategyProperties
     */
    public static StrategyProperties strategyProperties() {
        return new StrategyProperties.Builder().build();
    }

    /**
     * 全局配置 Builder
     *
     * @return GlobalProperties.Builder
     */
    public static GlobalProperties.Builder globalPropertiesBuilder() {
        return new GlobalProperties.Builder();
    }

    /**
     * 包相关的配置项 Builder
     *
     * @return PackageProperties.Builder
     */
    public static PackageProperties.Builder packagePropertiesBuilder() {
        return new PackageProperties.Builder();
    }

    /**
     * 策略配置项 Builder
     *
     * @return StrategyProperties.Builder
     */
    public static StrategyProperties.Builder strategyPropertiesBuilder() {
        return new StrategyProperties.Builder();
    }
}
