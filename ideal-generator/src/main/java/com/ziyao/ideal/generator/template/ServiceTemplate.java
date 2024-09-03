package com.ziyao.ideal.generator.template;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.core.Naming;
import com.ziyao.ideal.generator.core.OutputNameConvertor;
import com.ziyao.ideal.generator.core.Templates;
import com.ziyao.ideal.generator.core.GeneratorContext;
import com.ziyao.ideal.generator.settings.StrategySettings;
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
    public String getTemplate() {
        if (Strings.isEmpty(template)) {
            return Templates.service.getTemplate();
        }
        return template;
    }

    @Override
    public Map<String, Object> load(GeneratorContext generatorContext) {
        Map<String, Object> render = super.load(generatorContext);
        render.put("serviceName", generatorContext.getServiceName());
        render.put("serviceImplName", generatorContext.getServiceImplName());
        if (Strings.hasText(superClass)) {
            String[] array = superClass.split("\\.");
            render.put("superServiceClass", array[array.length - 1]);
            render.put("superServiceClassPackage", superClass);
        }
        return render;
    }


    public static class Builder extends AbstractTemplateBuilder {

        private final ServiceTemplate template = new ServiceTemplate();

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

        public Builder convertor(@NonNull OutputNameConvertor outputNameConvertor) {
            this.template.convertor = outputNameConvertor;
            return this;
        }

        public Builder template(@NonNull String template) {
            this.template.template = template;
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
