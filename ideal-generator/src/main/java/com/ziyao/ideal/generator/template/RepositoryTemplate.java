package com.ziyao.ideal.generator.template;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.core.Naming;
import com.ziyao.ideal.generator.core.OutputNameConvertor;
import com.ziyao.ideal.generator.core.Templates;
import com.ziyao.ideal.generator.core.meta.TemplateContext;
import com.ziyao.ideal.generator.settings.StrategySettings;
import lombok.Getter;

import java.util.Map;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class RepositoryTemplate extends AbstractTemplate {

    private OutputNameConvertor convertor = Naming.Repository.getConverter();
    private String xmlTemplate = Templates.mapper_xml.getTemplate();

    @Override
    public OutputNameConvertor getConvertor() {
        return convertor;
    }

    @Override
    public String getTemplate() {
        if (Strings.isEmpty(template)) {
            return Templates.repository.getTemplate();
        }
        return template;
    }

    @Override
    public String getSuperClass() {
        return super.getSuperClass();
    }

    @Override
    public Map<String, Object> load(TemplateContext context) {
        Map<String, Object> render = super.load(context);
        render.put("repositoryName", context.getRepositoryName());
        render.put("superRepositoryClass", superClass);
        render.put("mapperName", Naming.Mapper.getConverter().convert(context.getEntityName()));
        return render;
    }

    public static class Builder extends AbstractTemplateBuilder {
        private final RepositoryTemplate template = new RepositoryTemplate();

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

        public Builder superClass(Class<?> superClass) {
            this.template.superClass = superClass.getName();
            return this;
        }

        public Builder superClass(String superClass) {
            this.template.superClass = superClass;
            return this;
        }

        public Builder template(String template) {
            this.template.template = template;
            return this;
        }

        public Builder xmlTemplate(String template) {
            this.template.xmlTemplate = template;
            return this;
        }

        public Builder convertor(@NonNull OutputNameConvertor outputNameConvertor) {
            this.template.convertor = outputNameConvertor;
            return this;
        }

        public RepositoryTemplate get() {
            return template;
        }
    }

}
