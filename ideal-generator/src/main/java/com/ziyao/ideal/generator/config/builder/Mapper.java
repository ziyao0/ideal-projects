package com.ziyao.ideal.generator.config.builder;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.ITemplate;
import com.ziyao.ideal.generator.config.ConstVal;
import com.ziyao.ideal.generator.config.StrategyConfig;
import com.ziyao.ideal.generator.config.po.TableInfo;
import com.ziyao.ideal.generator.NameEnum;
import com.ziyao.ideal.generator.Templates;
import com.ziyao.ideal.generator.NameConvertor;
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
     * Mapper标记注解
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
    private NameConvertor converterMapperFileName = NameEnum.Mapper.getConverter();

    /**
     * 转换输出Xml文件名称
     */
    @Getter
    private NameConvertor converterXmlFileName = NameEnum.Mapper.getConverter();

    /**
     * 是否覆盖已有文件（默认 false）
     */
    @Getter
    private boolean fileOverride;

    /**
     * 设置缓存实现类
     */
    private Class<? extends Cache> cache;

    /**
     * 是否生成XML
     */
    @Getter
    private boolean generateMapperXml = true;

    /**
     * 是否生成Mapper
     */
    @Getter
    private boolean generateMapper = true;

    /**
     * Mapper模板路径
     */
    @Getter
    private String mapperTemplate = Templates.mapper_java.getTemplate();

    /**
     * MapperXml模板路径
     */
    @Getter
    private String mapperXmlTemplate = Templates.mapper_xml.getTemplate();

    @NonNull
    public String getSuperClass() {
        return superClass;
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
         * 标记 Mapper 注解
         *
         * @param annotationClass 注解Class
         * @return this
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
        public Builder convertMapperFileName(@NonNull NameConvertor converter) {
            this.mapper.converterMapperFileName = converter;
            return this;
        }

        /**
         * 转换Xml文件名称处理
         *
         * @param converter 　转换处理
         * @return this
         */
        public Builder convertXmlFileName(@NonNull NameConvertor converter) {
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
         */
        public Builder mapperTemplate(@NonNull String template) {
            this.mapper.mapperTemplate = template;
            return this;
        }

        /**
         * ServiceImpl模板路径
         *
         * @return this
         */
        public Builder mapperXmlTemplate(@NonNull String template) {
            this.mapper.mapperXmlTemplate = template;
            return this;
        }

        /**
         * 禁用Mapper生成
         *
         * @return this
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
         */
        public Builder disableMapper() {
            this.mapper.generateMapper = false;
            return this;
        }

        /**
         * 禁用MapperXml生成
         *
         * @return this
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
