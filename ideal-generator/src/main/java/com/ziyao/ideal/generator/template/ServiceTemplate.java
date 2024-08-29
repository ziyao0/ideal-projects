package com.ziyao.ideal.generator.template;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.NameConvertor;
import com.ziyao.ideal.generator.NameEnum;
import com.ziyao.ideal.generator.metadata.Metadata;
import lombok.Getter;

import java.util.Map;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class ServiceTemplate extends AbstractTemplate {


    private NameConvertor convertor = NameEnum.Service.getConverter();
    @Getter
    private NameConvertor serviceImplConvertor = NameEnum.ServiceImpl.getConverter();

    @Override
    public NameConvertor getConvertor() {
        return convertor;
    }

    @Override
    public Map<String, Object> load(Metadata metadata) {
        Map<String, Object> render = super.load(metadata);
        render.put("serviceName", this.getConvertor().convert(metadata.getEntityName()));
        return render;
    }


    public static class Builder extends AbstractTemplateBuilder {

        private final ServiceTemplate template = new ServiceTemplate();

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

        public Builder serviceImplConvertor(@NonNull NameConvertor nameConvertor) {
            this.template.serviceImplConvertor = nameConvertor;
            return this;
        }

        public ServiceTemplate get() {
            return template;
        }
    }

}
