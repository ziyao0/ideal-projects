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
public class ServiceTemplate extends AbstractTemplate {


    private OutputNameConvertor convertor = Naming.Service.getConverter();
    private OutputNameConvertor serviceImplConvertor = Naming.ServiceImpl.getConverter();
    private String implTemplate = Templates.service_impl.getTemplate();

    @Override
    public OutputNameConvertor getConvertor() {
        return convertor;
    }

    @Override
    public Map<String, Object> load(MetaInfo metaInfo) {
        Map<String, Object> render = super.load(metaInfo);
        render.put("serviceName", metaInfo.getServiceName());
        return render;
    }


    public static class Builder extends AbstractTemplateBuilder {

        private final ServiceTemplate template = new ServiceTemplate();

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

        public Builder convertor(@NonNull OutputNameConvertor outputNameConvertor) {
            this.template.convertor = outputNameConvertor;
            return this;
        }

        public Builder implTemplate(@NonNull String template) {
            this.template.implTemplate = template;
            return this;
        }

        public Builder serviceImplConvertor(@NonNull OutputNameConvertor outputNameConvertor) {
            this.template.serviceImplConvertor = outputNameConvertor;
            return this;
        }

        public ServiceTemplate get() {
            return template;
        }
    }

}
