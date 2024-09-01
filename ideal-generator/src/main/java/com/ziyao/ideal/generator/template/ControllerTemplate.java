package com.ziyao.ideal.generator.template;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.core.Naming;
import com.ziyao.ideal.generator.core.OutputNameConvertor;
import com.ziyao.ideal.generator.core.Templates;
import com.ziyao.ideal.generator.core.meta.TemplateContext;
import com.ziyao.ideal.generator.settings.StrategySettings;

import java.util.Map;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class ControllerTemplate extends AbstractTemplate {

    private OutputNameConvertor convertor = Naming.Controller.getConverter();

    @Override
    public OutputNameConvertor getConvertor() {
        return convertor;
    }

    @Override
    public String getTemplate() {
        if (Strings.isEmpty(template)) {
            return Templates.controller.getTemplate();
        }
        return template;
    }

    @Override
    public Map<String, Object> load(TemplateContext templateContext) {
        Map<String, Object> render = super.load(templateContext);
        render.put("controllerName", getConvertor().convert(templateContext.getEntityName()));
        return render;
    }

    public static class Builder extends AbstractTemplateBuilder {

        private final ControllerTemplate template = new ControllerTemplate();

        public Builder(StrategySettings strategy) {
            super(strategy);
        }

        public Builder disable() {
            this.template.generate = false;
            return this;
        }

        public Builder override() {
            this.template.override = true;
            return this;
        }

        public Builder template(String template) {
            this.template.template = template;
            return this;
        }

        public Builder convertor(@NonNull OutputNameConvertor outputNameConvertor) {
            this.template.convertor = outputNameConvertor;
            return this;
        }

        public ControllerTemplate get() {
            return template;
        }
    }

}
