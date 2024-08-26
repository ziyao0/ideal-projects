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
package com.ziyao.ideal.generator;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.config.*;
import com.ziyao.ideal.generator.config.builder.ConfigBuilder;
import com.ziyao.ideal.generator.config.po.TableInfo;
import com.ziyao.ideal.generator.engine.AbstractTemplateEngine;
import com.ziyao.ideal.generator.engine.FreemarkerTemplateEngine;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 生成文件
 *
 */
@Getter
public class AutoGenerator {

    private static final Logger logger = LoggerFactory.getLogger(AutoGenerator.class);

    /**
     * 配置信息
     */
    protected ConfigBuilder config;
    /**
     * 注入配置
     */
    protected InjectionConfig injection;
    /**
     * 数据源配置
     */
    private DataSourceConfig dataSource;
    /**
     * 数据库表配置
     */
    private StrategyConfig strategy;
    /**
     * 包 相关配置
     */
    private PackageConfig packageInfo;
    /**
     * 模板 相关配置
     *
     * @deprecated 3.5.6 {@link #strategy}
     */
    @Deprecated
    private TemplateConfig template;
    /**
     * 全局 相关配置
     */
    private GlobalConfig globalConfig;

    private AutoGenerator() {
        // 不推荐使用
    }

    /**
     * 构造方法
     *
     * @param dataSourceConfig 数据库配置
     */
    public AutoGenerator(@NonNull DataSourceConfig dataSourceConfig) {
        //这个是必须参数,其他都是可选的,后续去除默认构造更改成final
        this.dataSource = dataSourceConfig;
    }

    /**
     * 注入配置
     *
     * @param injectionConfig 注入配置
     * @return this
     */
    public AutoGenerator injection(@NonNull InjectionConfig injectionConfig) {
        this.injection = injectionConfig;
        return this;
    }

    /**
     * 生成策略
     *
     * @param strategyConfig 策略配置
     * @return this
     */
    public AutoGenerator strategy(@NonNull StrategyConfig strategyConfig) {
        this.strategy = strategyConfig;
        return this;
    }

    /**
     * 指定包配置信息
     *
     * @param packageConfig 包配置
     * @return this
     */
    public AutoGenerator packageInfo(@NonNull PackageConfig packageConfig) {
        this.packageInfo = packageConfig;
        return this;
    }

    /**
     * 指定模板配置
     *
     * @param templateConfig 模板配置
     * @return this
     * <p>
     * deprecated 3.5.6 {@link #strategy(StrategyConfig)}
     */
    public AutoGenerator template(@NonNull TemplateConfig templateConfig) {
        this.template = templateConfig;
        return this;
    }

    /**
     * 指定全局配置
     *
     * @param globalConfig 全局配置
     * @return this
     * @see 3.5.0
     */
    public AutoGenerator global(@NonNull GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
        return this;
    }

    /**
     * 设置配置汇总
     *
     * @param configBuilder 配置汇总
     * @return this
     */
    public AutoGenerator config(@NonNull ConfigBuilder configBuilder) {
        this.config = configBuilder;
        return this;
    }

    /**
     * 生成代码
     */
    public void execute() {
        this.execute(null);
    }

    /**
     * 生成代码
     *
     * @param templateEngine 模板引擎
     */
    public void execute(AbstractTemplateEngine templateEngine) {
        logger.debug("==========================准备生成文件...==========================");
        // 初始化配置
        if (null == config) {
            config = new ConfigBuilder(packageInfo, dataSource, strategy, template, globalConfig, injection);
        }
        if (null == templateEngine) {
            templateEngine = new FreemarkerTemplateEngine();
        }
        templateEngine.setConfigBuilder(config);
        // 模板引擎初始化执行文件输出
        templateEngine.init(config).batchOutput().open();
        logger.debug("==========================文件生成完成！！！==========================");
    }

    /**
     * 开放表信息、预留子类重写
     *
     * @param config 配置信息
     * @return ignore
     */
    @NonNull
    protected List<TableInfo> getAllTableInfoList(@NonNull ConfigBuilder config) {
        return config.getTableInfoList();
    }

    public InjectionConfig getInjectionConfig() {
        return injection;
    }

}
