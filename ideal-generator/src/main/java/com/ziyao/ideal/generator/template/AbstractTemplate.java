package com.ziyao.ideal.generator.template;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.core.Template;
import com.ziyao.ideal.generator.core.meta.TemplateContext;
import com.ziyao.ideal.generator.settings.StrategySettings;

import java.util.HashMap;
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
    public Map<String, Object> load(TemplateContext templateContext) {
        Map<String, Object> data = new HashMap<>();
        data.put("isSupperClass", Strings.hasText(superClass));
        return data;
    }

    public static abstract class AbstractTemplateBuilder {

        private final StrategySettings strategy;

        public AbstractTemplateBuilder(@NonNull StrategySettings strategy) {
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

        public StrategySettings build() {
            return this.strategy;
        }
    }

}
