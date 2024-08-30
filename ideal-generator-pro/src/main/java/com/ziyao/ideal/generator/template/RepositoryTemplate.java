package com.ziyao.ideal.generator.template;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.core.Naming;
import com.ziyao.ideal.generator.core.OutputNameConvertor;
import com.ziyao.ideal.generator.core.Templates;
import com.ziyao.ideal.generator.core.metadata.MetaInfo;
import com.ziyao.ideal.generator.properties.StrategyProperties;
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
    public Map<String, Object> load(MetaInfo metaInfo) {
        Map<String, Object> render = super.load(metaInfo);
        render.put("repositoryName", metaInfo.getRepositoryName());
        return render;
    }

    public static class Builder extends AbstractTemplateBuilder {
        private final RepositoryTemplate template = new RepositoryTemplate();

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
