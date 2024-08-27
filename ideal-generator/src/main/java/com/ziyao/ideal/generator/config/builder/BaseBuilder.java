package com.ziyao.ideal.generator.config.builder;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.config.IConfigBuilder;
import com.ziyao.ideal.generator.config.StrategyConfig;

/**
 * 配置构建
 */
public class BaseBuilder implements IConfigBuilder<StrategyConfig> {

    private final StrategyConfig strategyConfig;

    public BaseBuilder(@NonNull StrategyConfig strategyConfig) {
        this.strategyConfig = strategyConfig;
    }

    @NonNull
    public Entity.Builder entityBuilder() {
        return strategyConfig.entityBuilder();
    }

    @NonNull
    public Controller.Builder controllerBuilder() {
        return strategyConfig.controllerBuilder();
    }

    @NonNull
    public Mapper.Builder mapperBuilder() {
        return strategyConfig.mapperBuilder();
    }

    @NonNull
    public Service.Builder serviceBuilder() {
        return strategyConfig.serviceBuilder();
    }

    @NonNull
    public Repository.Builder repositoryBuilder() {
        return strategyConfig.repositoryBuilder();
    }

    @NonNull
    @Override
    public StrategyConfig build() {
        this.strategyConfig.validate();
        return this.strategyConfig;
    }
}
