/*
 * Copyright (c) 2011-2024, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ziyao.ideal.generator.config.builder;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.config.IConfigBuilder;
import com.ziyao.ideal.generator.config.StrategyConfig;

/**
 * 配置构建
 * <p>
 * 2020/10/11.
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
    @Override
    public StrategyConfig build() {
        this.strategyConfig.validate();
        return this.strategyConfig;
    }
}
