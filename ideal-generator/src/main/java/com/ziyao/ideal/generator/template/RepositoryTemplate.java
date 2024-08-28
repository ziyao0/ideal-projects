package com.ziyao.ideal.generator.template;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.metadata.Metadata;
import com.ziyao.ideal.generator.NameConvertor;
import com.ziyao.ideal.generator.NameEnum;

import java.util.Map;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class RepositoryTemplate extends AbstractTemplate {

    private NameConvertor convertor = NameEnum.Repository.getConverter();

    @Override
    public NameConvertor getConvertor() {
        return convertor;
    }

    @Override
    public Map<String, Object> load(Metadata metadata) {
        Map<String, Object> render = super.load(metadata);
        render.put("repositoryName", this.getConvertor().convert(metadata.getEntityName()));
        return render;
    }

    public static class Builder extends AbstractTemplateBuilder {
        private final RepositoryTemplate template = new RepositoryTemplate();

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

        public Builder convertor(@NonNull NameConvertor nameConvertor) {
            this.template.convertor = nameConvertor;
            return this;
        }

        public RepositoryTemplate get() {
            return template;
        }
    }

}
