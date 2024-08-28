package com.ziyao.ideal.generator.config.builder;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.ITemplate;
import com.ziyao.ideal.generator.config.StrategyConfig;
import com.ziyao.ideal.generator.config.po.TableInfo;
import com.ziyao.ideal.generator.NameEnum;
import com.ziyao.ideal.generator.Templates;
import com.ziyao.ideal.generator.NameConvertor;
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

    private String template = Templates.repository.getTemplate();

    private boolean fileOverride = false;
    /**
     * 转换输出控制器文件名称
     */
    private NameConvertor nameConvertor = NameEnum.Repository.getConverter();

    /**
     * 自定义继承的Mapper类全称，带包名
     */
    private String superClass;

    public static class Builder extends BaseBuilder {
        private final Repository repository = new Repository();

        public Builder(StrategyConfig strategyConfig) {
            super(strategyConfig);
        }

        public Builder template(@NonNull String template) {
            this.repository.template = template;
            return this;
        }

        /**
         * 转换输出文件名称
         *
         * @param converter 　转换处理
         * @return this
         */
        public Builder convertFileName(@NonNull NameConvertor converter) {
            this.repository.nameConvertor = converter;
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
    public NameConvertor getNameConvertor() {
        return nameConvertor;
    }


    @Override
    public @NonNull Map<String, Object> renderData(@NonNull TableInfo tableInfo) {
        return Map.of();
    }
}
