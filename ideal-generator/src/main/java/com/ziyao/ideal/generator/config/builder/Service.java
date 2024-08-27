package com.ziyao.ideal.generator.config.builder;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.ITemplate;
import com.ziyao.ideal.generator.config.ConstVal;
import com.ziyao.ideal.generator.config.StrategyConfig;
import com.ziyao.ideal.generator.config.po.TableInfo;
import com.ziyao.ideal.generator.core.NameTemplate;
import com.ziyao.ideal.generator.core.Template;
import com.ziyao.ideal.generator.function.ConverterFileName;
import com.ziyao.ideal.generator.util.ClassUtils;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Service属性配置
 */
@Getter
public class Service implements ITemplate {

    private final static Logger LOGGER = LoggerFactory.getLogger(Service.class);

    private Service() {
    }

    /**
     * 是否生成serviceImpl
     */

    private boolean generateServiceImpl = true;


    /**
     * 是否生成service
     */

    private boolean generateService = true;

    /**
     *
     */

    private String serviceTemplate = Template.service.getTemplate();


    private String serviceImplTemplate = Template.service_impl.getTemplate();


    /**
     * 自定义继承的Service类全称，带包名
     */
    private String superServiceClass = ConstVal.SUPER_SERVICE_CLASS;

    /**
     * 自定义继承的ServiceImpl类全称，带包名
     */
    private String superServiceImplClass = ConstVal.SUPER_SERVICE_IMPL_CLASS;

    @NonNull
    public String getSuperServiceClass() {
        return superServiceClass;
    }

    @NonNull
    public String getSuperServiceImplClass() {
        return superServiceImplClass;
    }

    /**
     * 转换输出Service文件名称
     */
    private ConverterFileName converterServiceFileName = NameTemplate.Service.getConverter();

    /**
     * 转换输出ServiceImpl文件名称
     */
    private ConverterFileName converterServiceImplFileName = NameTemplate.ServiceImpl.getConverter();

    /**
     * 是否覆盖已有文件（默认 false）
     */

    private boolean fileOverride;

    @NonNull
    public ConverterFileName getConverterServiceFileName() {
        return converterServiceFileName;
    }

    @NonNull
    public ConverterFileName getConverterServiceImplFileName() {
        return converterServiceImplFileName;
    }

    @Override
    @NonNull
    public Map<String, Object> renderData(@NonNull TableInfo tableInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("superServiceClassPackage", this.superServiceClass);
        data.put("superServiceClass", ClassUtils.getSimpleName(this.superServiceClass));
        data.put("superServiceImplClassPackage", this.superServiceImplClass);
        data.put("superServiceImplClass", ClassUtils.getSimpleName(this.superServiceImplClass));
        data.put("generateServiceImpl", this.generateServiceImpl);
        data.put("generateService", this.generateService);
        return data;
    }

    public static class Builder extends BaseBuilder {

        private final Service service = new Service();

        public Builder(@NonNull StrategyConfig strategyConfig) {
            super(strategyConfig);
        }

        /**
         * Service接口父类
         *
         * @param clazz 类
         * @return this
         */
        public Builder superServiceClass(@NonNull Class<?> clazz) {
            return superServiceClass(clazz.getName());
        }

        /**
         * Service接口父类
         *
         * @param superServiceClass 类名
         * @return this
         */
        public Builder superServiceClass(@NonNull String superServiceClass) {
            this.service.superServiceClass = superServiceClass;
            return this;
        }

        /**
         * Service实现类父类
         *
         * @param clazz 类
         * @return this
         */
        public Builder superServiceImplClass(@NonNull Class<?> clazz) {
            return superServiceImplClass(clazz.getName());
        }

        /**
         * Service实现类父类
         *
         * @param superServiceImplClass 类名
         * @return this
         */
        public Builder superServiceImplClass(@NonNull String superServiceImplClass) {
            this.service.superServiceImplClass = superServiceImplClass;
            return this;
        }

        /**
         * 转换输出service接口文件名称
         *
         * @param converter 　转换处理
         * @return this
         */
        public Builder convertServiceFileName(@NonNull ConverterFileName converter) {
            this.service.converterServiceFileName = converter;
            return this;
        }

        /**
         * 转换输出service实现类文件名称
         *
         * @param converter 　转换处理
         * @return this
         */
        public Builder convertServiceImplFileName(@NonNull ConverterFileName converter) {
            this.service.converterServiceImplFileName = converter;
            return this;
        }

        /**
         * 格式化service接口文件名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatServiceFileName(@NonNull String format) {
            return convertServiceFileName((entityName) -> String.format(format, entityName));
        }

        /**
         * 格式化service实现类文件名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatServiceImplFileName(@NonNull String format) {
            return convertServiceImplFileName((entityName) -> String.format(format, entityName));
        }

        /**
         * 覆盖已有文件（该方法后续会删除，替代方法为enableFileOverride方法）
         *
         * @see #enableFileOverride()
         */
        @Deprecated
        public Builder fileOverride() {
            LOGGER.warn("fileOverride方法后续会删除，替代方法为enableFileOverride方法");
            this.service.fileOverride = true;
            return this;
        }

        /**
         * 覆盖已有文件
         */
        public Builder enableFileOverride() {
            this.service.fileOverride = true;
            return this;
        }

        /**
         * 禁用生成Service
         *
         * @return this
         */
        public Builder disable() {
            this.service.generateService = false;
            this.service.generateServiceImpl = false;
            return this;
        }

        /**
         * 禁用生成
         *
         * @return this
         */
        public Builder disableService() {
            this.service.generateService = false;
            return this;
        }

        /**
         * 禁用生成ServiceImpl
         *
         * @return this
         */
        public Builder disableServiceImpl() {
            this.service.generateServiceImpl = false;
            return this;
        }

        /**
         * Service模板路径
         *
         * @return this
         */
        public Builder serviceTemplate(@NonNull String template) {
            this.service.serviceTemplate = template;
            return this;
        }

        /**
         * ServiceImpl模板路径
         *
         * @return this
         */
        public Builder serviceImplTemplate(@NonNull String template) {
            this.service.serviceImplTemplate = template;
            return this;
        }


        @NonNull
        public Service get() {
            return this.service;
        }
    }
}
