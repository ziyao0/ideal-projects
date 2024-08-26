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
package com.ziyao.ideal.generator.config;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.config.builder.Controller;
import com.ziyao.ideal.generator.config.builder.Entity;
import com.ziyao.ideal.generator.config.builder.Mapper;
import com.ziyao.ideal.generator.config.builder.Service;
import com.ziyao.ideal.generator.core.Template;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模板路径配置项
 *
 * @author tzg hubin
 * @see StrategyConfig.Builder#entityBuilder()
 * @see StrategyConfig.Builder#serviceBuilder()
 * @see StrategyConfig.Builder#mapperBuilder()
 * @see StrategyConfig.Builder#controllerBuilder()
 * @since 2017-06-17
 * deprecated 3.5.6 {@link StrategyConfig}
 */
@Getter
public class TemplateConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateConfig.class);

    /**
     * 设置实体模板路径
     */
    private String entity;

    private String dto;

    private String repository;

    /**
     * 设置控制器模板路径
     */

    private String controller;

    /**
     * 设置Mapper模板路径
     */

    private String mapper;

    /**
     * 设置MapperXml模板路径
     */

    private String xml;

    /**
     * 设置Service模板路径
     */

    private String service;

    /**
     * 设置ServiceImpl模板路径
     */

    private String serviceImpl;

    /**
     * 是否禁用实体模板（默认 false）
     */
    private boolean disableEntity;

    /**
     * 不对外爆露
     */
    private TemplateConfig() {
        this.entity = Template.entity.getTemplate();
        this.dto = Template.entity_dto.getTemplate();
        this.repository = Template.repository.getTemplate();
        this.mapper = Template.mapper_java.getTemplate();
        this.xml = Template.mapper_xml.getTemplate();
        this.service = Template.service.getTemplate();
        this.serviceImpl = Template.service_impl.getTemplate();
        this.controller = Template.controller.getTemplate();
    }

    /**
     * 当模板赋值为空时进行日志提示打印
     *
     * @param value        模板值
     * @param templateType 模板类型
     */
    private void logger(String value, TemplateType templateType) {
        if (Strings.isEmpty(value)) {
            LOGGER.warn("推荐使用disable(TemplateType.{})方法进行默认模板禁用.", templateType.name());
        }
    }

    /**
     * 禁用模板
     *
     * @param templateTypes 模板类型
     * @return this
     * @see Service.Builder#disable()
     * @see Service.Builder#disableService()
     * @see Service.Builder#disableServiceImpl()
     * @see Controller.Builder#disable()
     * @see Mapper.Builder#disable()
     * @see Mapper.Builder#disableMapper()
     * @see Mapper.Builder#disableMapperXml()
     * @deprecated 3.5.6
     */
    @Deprecated
    public TemplateConfig disable(@NonNull TemplateType... templateTypes) {
        for (TemplateType templateType : templateTypes) {
            switch (templateType) {
                case ENTITY:
                    this.entity = null;
                    //暂时没其他多的需求,使用一个单独的boolean变量进行支持一下.
                    this.disableEntity = true;
                    break;
                case CONTROLLER:
                    this.controller = null;
                    break;
                case MAPPER:
                    this.mapper = null;
                    break;
                case XML:
                    this.xml = null;
                    break;
                case SERVICE:
                    this.service = null;
                    break;
                case SERVICE_IMPL:
                    this.serviceImpl = null;
                    break;
                default:
            }
        }
        return this;
    }


    /**
     * 模板路径配置构建者
     * <p>
     * 3.5.0
     * deprecated 3.5.6 {@link StrategyConfig}
     */
    public static class Builder implements IConfigBuilder<TemplateConfig> {

        private final TemplateConfig templateConfig;

        /**
         * 默认生成一个空的
         */
        public Builder() {
            this.templateConfig = new TemplateConfig();
        }

        /**
         * 禁用模板
         *
         * @return this
         */
        public Builder disable(@NonNull TemplateType... templateTypes) {
            this.templateConfig.disable(templateTypes);
            return this;
        }


        /**
         * 设置service模板路径
         *
         * @param serviceTemplate service接口模板路径
         * @return this
         * @deprecated {@link Service.Builder#serviceTemplate(String)}
         */
        @Deprecated
        public Builder service(@NonNull String serviceTemplate) {
            this.templateConfig.service = serviceTemplate;
            return this;
        }

        /**
         * 设置serviceImpl模板路径
         *
         * @param serviceImplTemplate service实现类模板路径
         * @return this
         * @deprecated {@link Service.Builder#serviceImplTemplate(String)}
         */
        @Deprecated
        public Builder serviceImpl(@NonNull String serviceImplTemplate) {
            this.templateConfig.serviceImpl = serviceImplTemplate;
            return this;
        }

        /**
         * 设置mapper模板路径
         *
         * @param mapperTemplate mapper模板路径
         * @return this
         * @deprecated {@link Mapper.Builder#mapperTemplate(String)}
         */
        @Deprecated
        public Builder mapper(@NonNull String mapperTemplate) {
            this.templateConfig.mapper = mapperTemplate;
            return this;
        }

        /**
         * 设置mapperXml模板路径
         *
         * @param xmlTemplate xml模板路径
         * @return this
         * @deprecated {@link Mapper.Builder#mapperXmlTemplate(String)}
         */
        @Deprecated
        public Builder xml(@NonNull String xmlTemplate) {
            this.templateConfig.xml = xmlTemplate;
            return this;
        }

        /**
         * 设置控制器模板路径
         *
         * @param controllerTemplate 控制器模板路径
         * @return this
         * @deprecated {@link Controller.Builder#template(String)}
         */
        @Deprecated
        public Builder controller(@NonNull String controllerTemplate) {
            this.templateConfig.controller = controllerTemplate;
            return this;
        }

        /**
         * 构建模板配置对象
         *
         * @return 模板配置对象
         */
        @Override
        public TemplateConfig build() {
            return this.templateConfig;
        }
    }
}
