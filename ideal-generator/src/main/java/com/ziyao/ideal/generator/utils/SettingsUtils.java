package com.ziyao.ideal.generator.utils;

import com.ziyao.ideal.generator.settings.GlobalSettings;
import com.ziyao.ideal.generator.settings.PackageSettings;
import com.ziyao.ideal.generator.settings.StrategySettings;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public abstract class SettingsUtils {

    /**
     * 全局配置
     *
     * @return GlobalProperties
     */
    public static GlobalSettings globalProperties() {
        return new GlobalSettings.Builder().build();
    }

    /**
     * 包相关的配置项
     *
     * @return PackageProperties
     */
    public static PackageSettings packageProperties() {
        return new PackageSettings.Builder().build();
    }

    /**
     * 策略配置项
     *
     * @return StrategyProperties
     */
    public static StrategySettings strategyProperties() {
        return new StrategySettings.Builder().build();
    }

    /**
     * 全局配置 Builder
     *
     * @return GlobalProperties.Builder
     */
    public static GlobalSettings.Builder globalPropertiesBuilder() {
        return new GlobalSettings.Builder();
    }

    /**
     * 包相关的配置项 Builder
     *
     * @return PackageProperties.Builder
     */
    public static PackageSettings.Builder packagePropertiesBuilder() {
        return new PackageSettings.Builder();
    }

    /**
     * 策略配置项 Builder
     *
     * @return StrategyProperties.Builder
     */
    public static StrategySettings.Builder strategyPropertiesBuilder() {
        return new StrategySettings.Builder();
    }
}
