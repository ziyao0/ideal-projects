package com.ziyao.ideal.generator.template;

import com.ziyao.ideal.generator.core.Naming;
import com.ziyao.ideal.generator.core.NamingStrategy;
import com.ziyao.ideal.generator.core.OutputNameConvertor;
import com.ziyao.ideal.generator.core.Templates;
import com.ziyao.ideal.generator.core.metadata.MetaInfo;
import com.ziyao.ideal.generator.properties.StrategyProperties;
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

    private NamingStrategy namingStrategy = NamingStrategy.underline_to_camel;
    private OutputNameConvertor convertor = Naming.Entity.getConverter();
    private OutputNameConvertor dtoConvertor = Naming.Dto.getConverter();
    private final Set<String> ignoreColumns = new HashSet<>();
    private String dtoTemplate = Templates.entity_dto.getTemplate();

    @Override
    public OutputNameConvertor getConvertor() {
        return convertor;
    }

    @Override
    public Map<String, Object> load(MetaInfo metaInfo) {
        Map<String, Object> render = super.load(metaInfo);
        render.put("entityName", metaInfo.getEntityName());
        return render;
    }

    public static class Builder extends AbstractTemplateBuilder {

        private final PersistentTemplate template = new PersistentTemplate();

        public Builder(StrategyProperties strategy) {
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
