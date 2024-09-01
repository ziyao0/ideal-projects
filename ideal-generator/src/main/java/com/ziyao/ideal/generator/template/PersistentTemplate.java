package com.ziyao.ideal.generator.template;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.generator.core.Naming;
import com.ziyao.ideal.generator.core.NamingStrategy;
import com.ziyao.ideal.generator.core.OutputNameConvertor;
import com.ziyao.ideal.generator.core.Templates;
import com.ziyao.ideal.generator.core.meta.TemplateContext;
import com.ziyao.ideal.generator.settings.StrategySettings;
import lombok.Getter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class PersistentTemplate extends AbstractTemplate {

    private OutputNameConvertor convertor = Naming.Entity.getConverter();
    private OutputNameConvertor dtoConvertor = Naming.Dto.getConverter();
    private final Set<String> ignoreColumns = new HashSet<>();
    private String dtoTemplate = Templates.entity_dto.getTemplate();
    private boolean serial = true;

    @Override
    public OutputNameConvertor getConvertor() {
        return convertor;
    }

    @Override
    public String getTemplate() {
        if (Strings.isEmpty(template)) {
            return Templates.entity.getTemplate();
        }
        return template;
    }

    public String getEntityName(String tableName) {
        return NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(tableName));
    }

    @Override
    public Map<String, Object> load(TemplateContext templateContext) {
        Map<String, Object> render = super.load(templateContext);
        render.put("entityName", templateContext.getEntityName());
        render.put("dtoName", templateContext.getDtoName());
        render.put("primaryPropertyType", templateContext.getPrimary().getPropertyType());
        render.put("serial", serial);
        return render;
    }

    public static class Builder extends AbstractTemplateBuilder {

        private final PersistentTemplate template = new PersistentTemplate();

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

        public Builder disableSerial() {
            this.template.serial = false;
            return this;
        }

        public Builder template(String template) {
            this.template.template = template;
            return this;
        }

        public Builder dtoTemplate(String template) {
            this.template.dtoTemplate = template;
            return this;
        }

        public Builder convertor(OutputNameConvertor outputNameConvertor) {
            this.template.convertor = outputNameConvertor;
            return this;
        }

        public Builder dtoConvertor(OutputNameConvertor outputNameConvertor) {
            this.template.dtoConvertor = outputNameConvertor;
            return this;
        }

        public PersistentTemplate get() {
            return template;
        }


    }

}
