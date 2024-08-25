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

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.core.lang.Nullable;
import com.ziyao.ideal.generator.ITemplate;
import com.ziyao.ideal.generator.config.ConstVal;
import com.ziyao.ideal.generator.config.StrategyConfig;
import com.ziyao.ideal.generator.config.po.TableInfo;
import com.ziyao.ideal.generator.function.ConverterFileName;
import com.ziyao.ideal.generator.util.ClassUtils;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制器属性配置
 * <p>
 * 2020/10/11.
 */
public class Controller implements ITemplate {

    private final static Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    private Controller() {
    }

    /**
     * 生成 <code>@RestController</code> 控制器（默认 false）
     * <pre>
     *      <code>@Controller</code> -> <code>@RestController</code>
     * </pre>
     */
    @Getter
    private boolean restStyle;

    /**
     * 驼峰转连字符（默认 false）
     * <pre>
     *      <code>@RequestMapping("/managerUserActionHistory")</code> -> <code>@RequestMapping("/manager-user-action-history")</code>
     * </pre>
     */
    @Getter
    private boolean hyphenStyle;

    /**
     * 自定义继承的Controller类全称，带包名
     */
    private String superClass;

    /**
     * 转换输出控制器文件名称
     */
    private ConverterFileName converterFileName = (entityName -> entityName + ConstVal.CONTROLLER);

    /**
     * 是否覆盖已有文件（默认 false）
     *
     
     */
    @Getter
    private boolean fileOverride;

    /**
     * 是否生成
     *
     * 
     */
    @Getter
    private boolean generate = true;

    /**
     * 模板路径
     *
     * 
     */
    @Getter
    private String templatePath = ConstVal.TEMPLATE_CONTROLLER;

    @Nullable
    public String getSuperClass() {
        return superClass;
    }

    @NonNull
    public ConverterFileName getConverterFileName() {
        return converterFileName;
    }

    @Override
    @NonNull
    public Map<String, Object> renderData(@NonNull TableInfo tableInfo) {
        Map<String, Object> data = new HashMap<>(5);
        data.put("controllerMappingHyphen", Strings.camelToHyphen(tableInfo.getEntityPath()));
        data.put("controllerMappingHyphenStyle", this.hyphenStyle);
        data.put("restControllerStyle", this.restStyle);
        data.put("superControllerClassPackage", Strings.isEmpty(superClass) ? null : superClass);
        data.put("superControllerClass", ClassUtils.getSimpleName(this.superClass));
        return data;
    }

    public static class Builder extends BaseBuilder {

        private final Controller controller = new Controller();

        public Builder(@NonNull StrategyConfig strategyConfig) {
            super(strategyConfig);
        }

        /**
         * 父类控制器
         *
         * @param clazz 父类控制器
         * @return this
         */
        public Builder superClass(@NonNull Class<?> clazz) {
            return superClass(clazz.getName());
        }

        /**
         * 父类控制器
         *
         * @param superClass 父类控制器类名
         * @return this
         */
        public Builder superClass(@NonNull String superClass) {
            this.controller.superClass = superClass;
            return this;
        }

        /**
         * 开启驼峰转连字符
         *
         * @return this
         */
        public Builder enableHyphenStyle() {
            this.controller.hyphenStyle = true;
            return this;
        }

        /**
         * 开启生成@RestController控制器
         *
         * @return this
         */
        public Builder enableRestStyle() {
            this.controller.restStyle = true;
            return this;
        }

        /**
         * 转换输出文件名称
         *
         * @param converter 　转换处理
         * @return this
         */
        public Builder convertFileName(@NonNull ConverterFileName converter) {
            this.controller.converterFileName = converter;
            return this;
        }

        /**
         * 格式化文件名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatFileName(@NonNull String format) {
            return convertFileName((entityName) -> String.format(format, entityName));
        }

        /**
         * 覆盖已有文件（该方法后续会删除，替代方法为enableFileOverride方法）
         *
         * @see #enableFileOverride()
         */
        @Deprecated
        public Builder fileOverride() {
            LOGGER.warn("fileOverride方法后续会删除，替代方法为enableFileOverride方法");
            this.controller.fileOverride = true;
            return this;
        }

        /**
         * 覆盖已有文件
         *
         * 
         */
        public Builder enableFileOverride() {
            this.controller.fileOverride = true;
            return this;
        }

        /**
         * 禁用生成
         *
         * @return this
         * 
         */
        public Builder disable() {
            this.controller.generate = false;
            return this;
        }

        /**
         * 指定模板路径
         *
         * @param template 模板路径
         * @return this
         * 
         */
        public Builder template(@NonNull String template) {
            this.controller.templatePath = template;
            return this;
        }

        @NonNull
        public Controller get() {
            return this.controller;
        }
    }
}
