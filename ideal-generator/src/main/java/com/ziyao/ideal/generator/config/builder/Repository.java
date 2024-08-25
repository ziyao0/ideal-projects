package com.ziyao.ideal.generator.config.builder;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.ITemplate;
import com.ziyao.ideal.generator.config.ConstVal;
import com.ziyao.ideal.generator.config.StrategyConfig;
import com.ziyao.ideal.generator.config.po.TableInfo;
import com.ziyao.ideal.generator.function.ConverterFileName;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author ziyao zhang
 */
@Getter
public class Repository implements ITemplate {
    private final static Logger LOGGER = LoggerFactory.getLogger(Repository.class);

    private Repository() {
    }

    private boolean generateRepository = true;

    private String repositoryTemplate = ConstVal.TEMPLATE_REPOSITORY_JAVA_JPA;

    private boolean fileOverride = false;
    /**
     * 转换输出控制器文件名称
     */
    private ConverterFileName converterFileName = (entityName -> entityName + ConstVal.REPOSITORY);

    /**
     * 自定义继承的Mapper类全称，带包名
     */
    private String superClass;

    public static class Builder extends BaseBuilder {
        private final Repository repository = new Repository();

        public Builder(StrategyConfig strategyConfig) {
            super(strategyConfig);
        }

        public Builder repositoryTemplate(@NonNull String template) {
            this.repository.repositoryTemplate = template;
            return this;
        }

        /**
         * 转换输出文件名称
         *
         * @param converter 　转换处理
         * @return this
         */
        public Builder convertFileName(@NonNull ConverterFileName converter) {
            this.repository.converterFileName = converter;
            return this;
        }

        public Builder disable() {
            this.repository.generateRepository = false;
            return this;
        }

        public Builder enableFileOverride() {
            this.repository.fileOverride = true;
            return this;
        }

        public Repository get() {
            return this.repository;
        }

    }

    @NonNull
    public ConverterFileName getConverterFileName() {
        return converterFileName;
    }


    @Override
    public @NonNull Map<String, Object> renderData(@NonNull TableInfo tableInfo) {
        return Map.of();
    }
}
