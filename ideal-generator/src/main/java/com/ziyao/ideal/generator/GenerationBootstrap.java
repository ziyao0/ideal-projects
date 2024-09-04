package com.ziyao.ideal.generator;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.core.PersistType;
import com.ziyao.ideal.generator.settings.DataSourceSettings;
import com.ziyao.ideal.generator.settings.GlobalSettings;
import com.ziyao.ideal.generator.settings.PackageSettings;
import com.ziyao.ideal.generator.settings.StrategySettings;
import lombok.Getter;

import java.util.function.Consumer;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class GenerationBootstrap {

    private final DataSourceSettings.Builder dataSourcePropertiesBuilder;
    private final GlobalSettings.Builder globalPropertiesBuilder;
    private final PackageSettings.Builder packagePropertiesBuilder;
    private final StrategySettings.Builder strategyPropertiesBuilder;

    private GenerationBootstrap(DataSourceSettings.Builder dataSourcePropertiesBuilder) {
        this.dataSourcePropertiesBuilder = dataSourcePropertiesBuilder;
        this.globalPropertiesBuilder = new GlobalSettings.Builder();
        this.packagePropertiesBuilder = new PackageSettings.Builder();
        this.strategyPropertiesBuilder = new StrategySettings.Builder();
    }

    public GenerationBootstrap(DataSourceSettings.Builder dataSourcePropertiesBuilder,
                               GlobalSettings.Builder globalPropertiesBuilder,
                               PackageSettings.Builder packagePropertiesBuilder,
                               StrategySettings.Builder strategyPropertiesBuilder) {
        this.dataSourcePropertiesBuilder = dataSourcePropertiesBuilder;
        this.globalPropertiesBuilder = globalPropertiesBuilder;
        this.packagePropertiesBuilder = packagePropertiesBuilder;
        this.strategyPropertiesBuilder = strategyPropertiesBuilder;
    }

    public static GenerationBootstrap init(@NonNull String url, String username, String password) {
        return new GenerationBootstrap(new DataSourceSettings.Builder(url, username, password));
    }

    public static GenerationBootstrap init(@NonNull DataSourceSettings.Builder dataSourcePropertiesBuilder) {
        return new GenerationBootstrap(dataSourcePropertiesBuilder);
    }

    /**
     * 通过配置文件初始化配置信息
     *
     * @param location 配置文件路径
     * @return GenerationBootstrap
     */
    public static GenerationBootstrap init(String location) {
        DataSourceSettings dataSourceSettings = ConfigurationPropertiesBinder.bind(DataSourceSettings.class, location);
        GlobalSettings globalSettings = ConfigurationPropertiesBinder.bind(GlobalSettings.class, location);
        PackageSettings packageSettings = ConfigurationPropertiesBinder.bind(PackageSettings.class, location);
        StrategySettings strategySettings = ConfigurationPropertiesBinder.bind(StrategySettings.class, location);
        return new GenerationBootstrap(
                new DataSourceSettings.Builder(dataSourceSettings),
                new GlobalSettings.Builder(globalSettings),
                new PackageSettings.Builder(packageSettings),
                new StrategySettings.Builder(strategySettings));
    }

    /**
     * 全局配置
     *
     * @param consumer 自定义全局配置
     * @return GenerationBootstrap
     */
    public GenerationBootstrap dataSourceProperties(Consumer<DataSourceSettings.Builder> consumer) {
        consumer.accept(this.dataSourcePropertiesBuilder);
        return this;
    }


    /**
     * 全局配置
     *
     * @param consumer 自定义全局配置
     * @return AutoGenerator
     */
    public GenerationBootstrap globalProperties(Consumer<GlobalSettings.Builder> consumer) {
        consumer.accept(this.globalPropertiesBuilder);
        return this;
    }


    /**
     * 包配置
     *
     * @param consumer 自定义包配置
     * @return AutoGenerator
     */
    public GenerationBootstrap packageProperties(Consumer<PackageSettings.Builder> consumer) {
        consumer.accept(this.packagePropertiesBuilder);
        return this;
    }


    /**
     * 策略配置
     *
     * @param consumer 自定义策略配置
     * @return AutoGenerator
     */
    public GenerationBootstrap strategyProperties(Consumer<StrategySettings.Builder> consumer) {
        consumer.accept(this.strategyPropertiesBuilder);
        return this;
    }


    public void bootstrap() {
        new GenerationEngine(this.dataSourcePropertiesBuilder.build())
                // 全局配置
                .global(this.globalPropertiesBuilder.build())
                // 包配置
                .packages(this.packagePropertiesBuilder.build())
                // 策略配置
                .strategy(this.strategyPropertiesBuilder.build())
                // 执行
                .generate();
    }
}
