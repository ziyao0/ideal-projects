package com.ziyao.ideal.generator.template;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.Template;
import com.ziyao.ideal.generator.metadata.Metadata;

import java.util.Map;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public abstract class AbstractTemplate implements Template {

    protected boolean generate = true;
    protected boolean override;
    protected String superClass;
    protected String template;

    @Override
    public boolean isGenerate() {
        return this.generate;
    }

    @Override
    public boolean isOverride() {
        return override;
    }

    @Override
    public String getSuperClass() {
        return superClass;
    }

    @Override
    public String getTemplate() {
        return template;
    }

    @Override
    public Map<String, Object> load(Metadata metadata) {

        return Map.of();
    }

    public static abstract class AbstractTemplateBuilder {

        private final TemplateStrategy strategy;

        public AbstractTemplateBuilder(@NonNull TemplateStrategy strategy) {
            this.strategy = strategy;
        }

        @NonNull
        public PersistentTemplate.Builder persistentBuilder() {
            return strategy.persistentBuilder();
        }

        @NonNull
        public ControllerTemplate.Builder controllerBuilder() {
            return strategy.controllerBuilder();
        }

        @NonNull
        public ServiceTemplate.Builder serviceBuilder() {
            return strategy.serviceBuilder();
        }

        @NonNull
        public RepositoryTemplate.Builder repositoryBuilder() {
            return strategy.repositoryBuilder();
        }

        @NonNull
        public TemplateStrategy build() {
            return this.strategy;
        }
    }

}
