package com.ziyao.ideal.generator.template;

import com.ziyao.ideal.generator.NameConvertor;
import com.ziyao.ideal.generator.NameEnum;
import com.ziyao.ideal.generator.config.rules.NamingStrategy;
import com.ziyao.ideal.generator.metadata.Metadata;
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
    private NameConvertor convertor = NameEnum.Entity.getConverter();
    private NameConvertor dtoConvertor = NameEnum.Dto.getConverter();
    private final Set<String> ignoreColumns = new HashSet<>();

    @Override
    public NameConvertor getConvertor() {
        return convertor;
    }

    @Override
    public Map<String, Object> load(Metadata metadata) {
        Map<String, Object> render = super.load(metadata);
        render.put("entityName", metadata.getEntityName());
        return render;
    }

    public static class Builder extends AbstractTemplateBuilder {

        private final PersistentTemplate template = new PersistentTemplate();

        public Builder(TemplateStrategy strategy) {
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

        public Builder convertor(NameConvertor nameConvertor) {
            this.template.convertor = nameConvertor;
            return this;
        }

        public Builder dtoConvertor(NameConvertor nameConvertor) {
            this.template.dtoConvertor = nameConvertor;
            return this;
        }

        public PersistentTemplate get() {
            return template;
        }


    }

}
