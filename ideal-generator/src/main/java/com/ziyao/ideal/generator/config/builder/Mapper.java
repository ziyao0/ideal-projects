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
import com.ziyao.ideal.generator.ITemplate;
import com.ziyao.ideal.generator.config.ConstVal;
import com.ziyao.ideal.generator.config.StrategyConfig;
import com.ziyao.ideal.generator.config.po.TableInfo;
import com.ziyao.ideal.generator.function.ConverterFileName;
import com.ziyao.ideal.generator.util.ClassUtils;
import lombok.Getter;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.LoggingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * 控制器属性配置
 * <p>
 * 2020/10/11.
 */
public class Mapper implements ITemplate {

    private final static Logger LOGGER = LoggerFactory.getLogger(Mapper.class);

    private Mapper() {
    }

    /**
     * 自定义继承的Mapper类全称，带包名
     */
    private String superClass = ConstVal.SUPER_MAPPER_CLASS;

    /**
     * 是否添加 @Mapper 注解（默认 false）
     *
     * @see #mapperAnnotationClass
     * @since 3.5.1
     * @deprecated 3.5.4
     */
    @Deprecated
    private boolean mapperAnnotation;

    /**
     * Mapper标记注解
     *
     * @since 3.5.3
     */
    private Class<? extends Annotation> mapperAnnotationClass;

    /**
     * 是否开启BaseResultMap（默认 false）
     */
    @Getter
    private boolean baseResultMap;

    /**
     * 是否开启baseColumnList（默认 false）
     */
    @Getter
    private boolean baseColumnList;

    /**
     * 转换输出Mapper文件名称
     */
    @Getter
    private ConverterFileName converterMapperFileName = (entityName -> entityName + ConstVal.MAPPER);

    /**
     * 转换输出Xml文件名称
     */
    @Getter
    private ConverterFileName converterXmlFileName = (entityName -> entityName + ConstVal.MAPPER);

    /**
     * 是否覆盖已有文件（默认 false）
     *
     * @since 3.5.2
     */
    @Getter
    private boolean fileOverride;

    /**
     * 设置缓存实现类
     */
    private Class<? extends Cache> cache;

    /**
     * 是否生成XML
     *
     * @since 3.5.6
     */
    @Getter
    private boolean generateMapperXml = true;

    /**
     * 是否生成Mapper
     *
     * @since 3.5.6
     */
    @Getter
    private boolean generateMapper = true;

    /**
     * Mapper模板路径
     *
     * @since 3.5.6
     */
    @Getter
    private String mapperTemplatePath = ConstVal.TEMPLATE_MAPPER;

    /**
     * MapperXml模板路径
     *
     * @since 3.5.6
     */
    @Getter
    private String mapperXmlTemplatePath = ConstVal.TEMPLATE_XML;

    @NonNull
    public String getSuperClass() {
        return superClass;
    }

    @Deprecated
    public boolean isMapperAnnotation() {
        return mapperAnnotationClass != null;
    }

    public Class<? extends Cache> getCache() {
        return this.cache == null ? LoggingCache.class : this.cache;
    }

    @Override
    @NonNull
    public Map<String, Object> renderData(@NonNull TableInfo tableInfo) {
        Map<String, Object> data = new HashMap<>();
        boolean enableCache = this.cache != null;
        data.put("enableCache", enableCache);
        data.put("mapperAnnotation", mapperAnnotationClass != null);
        data.put("mapperAnnotationClass", mapperAnnotationClass);
        data.put("baseResultMap", this.baseResultMap);
        data.put("baseColumnList", this.baseColumnList);
        data.put("superMapperClassPackage", this.superClass);
        if (enableCache) {
            Class<? extends Cache> cacheClass = this.getCache();
            data.put("cache", cacheClass);
            data.put("cacheClassName", cacheClass.getName());
        }
        data.put("superMapperClass", ClassUtils.getSimpleName(this.superClass));
        data.put("generateMapperXml", this.generateMapperXml);
        data.put("generateMapper", this.generateMapper);
        return data;
    }

    public static class Builder extends BaseBuilder {

        private final Mapper mapper = new Mapper();

        public Builder(StrategyConfig strategyConfig) {
            super(strategyConfig);
        }

        /**
         * 父类Mapper
         *
         * @param superClass 类名
         * @return this
         */
        public Builder superClass(@NonNull String superClass) {
            this.mapper.superClass = superClass;
            return this;
        }

        /**
         * 父类Mapper
         *
         * @param superClass 类
         * @return this
         */
        public Builder superClass(@NonNull Class<?> superClass) {
            return superClass(superClass.getName());
        }

        /**
         * 开启 @Mapper 注解
         *
         * @return this
         * @see #mapperAnnotation(Class)
         * @since 3.5.1
         * @deprecated 3.5.4
         */
        @Deprecated
        public Builder enableMapperAnnotation() {
            this.mapper.mapperAnnotation = true;
            //TODO 因为现在mybatis-plus传递mybatis-spring依赖，这里是没问题的，但后面如果考虑脱离mybatis-spring的时候就需要把这里处理掉，建议使用mapperAnnotation方法来标记自己的注解。
            this.mapper.mapperAnnotationClass = org.apache.ibatis.annotations.Mapper.class;
            return this;
        }

        /**
         * 标记 Mapper 注解
         *
         * @param annotationClass 注解Class
         * @return this
         * @since 3.5.3
         */
        public Builder mapperAnnotation(Class<? extends Annotation> annotationClass) {
            this.mapper.mapperAnnotationClass = annotationClass;
            return this;
        }

        /**
         * 开启baseResultMap
         *
         * @return this
         */
        public Builder enableBaseResultMap() {
            this.mapper.baseResultMap = true;
            return this;
        }

        /**
         * 开启baseColumnList
         *
         * @return this
         */
        public Builder enableBaseColumnList() {
            this.mapper.baseColumnList = true;
            return this;
        }

        /**
         * 设置缓存实现类
         *
         * @param cache 缓存实现
         * @return this
         */
        public Builder cache(@NonNull Class<? extends Cache> cache) {
            this.mapper.cache = cache;
            return this;
        }

        /**
         * 输出Mapper文件名称转换
         *
         * @param converter 　转换处理
         * @return this
         */
        public Builder convertMapperFileName(@NonNull ConverterFileName converter) {
            this.mapper.converterMapperFileName = converter;
            return this;
        }

        /**
         * 转换Xml文件名称处理
         *
         * @param converter 　转换处理
         * @return this
         */
        public Builder convertXmlFileName(@NonNull ConverterFileName converter) {
            this.mapper.converterXmlFileName = converter;
            return this;
        }

        /**
         * 格式化Mapper文件名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatMapperFileName(@NonNull String format) {
            return convertMapperFileName((entityName) -> String.format(format, entityName));
        }

        /**
         * 格式化Xml文件名称
         *
         * @param format 格式
         * @return this
         */
        public Builder formatXmlFileName(@NonNull String format) {
            return convertXmlFileName((entityName) -> String.format(format, entityName));
        }

        /**
         * 覆盖已有文件（该方法后续会删除，替代方法为enableFileOverride方法）
         *
         * @see #enableFileOverride()
         */
        @Deprecated
        public Builder fileOverride() {
            LOGGER.warn("fileOverride方法后续会删除，替代方法为enableFileOverride方法");
            this.mapper.fileOverride = true;
            return this;
        }

        /**
         * 覆盖已有文件
         */
        public Builder enableFileOverride() {
            this.mapper.fileOverride = true;
            return this;
        }

        /**
         * Service模板路径
         *
         * @return this
         * @since 3.5.6
         */
        public Builder mapperTemplate(@NonNull String template) {
            this.mapper.mapperTemplatePath = template;
            return this;
        }

        /**
         * ServiceImpl模板路径
         *
         * @return this
         * @since 3.5.6
         */
        public Builder mapperXmlTemplate(@NonNull String template) {
            this.mapper.mapperXmlTemplatePath = template;
            return this;
        }

        /**
         * 禁用Mapper生成
         *
         * @return this
         * @since 3.5.6
         */
        public Builder disable() {
            this.mapper.generateMapper = false;
            this.mapper.generateMapperXml = false;
            return this;
        }

        /**
         * 禁用Mapper接口生成
         *
         * @return this
         * @since 3.5.6
         */
        public Builder disableMapper() {
            this.mapper.generateMapper = false;
            return this;
        }

        /**
         * 禁用MapperXml生成
         *
         * @return this
         * @since 3.5.6
         */
        public Builder disableMapperXml() {
            this.mapper.generateMapperXml = false;
            return this;
        }

        @NonNull
        public Mapper get() {
            return this.mapper;
        }
    }
}
