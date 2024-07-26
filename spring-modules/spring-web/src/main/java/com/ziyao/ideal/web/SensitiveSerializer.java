package com.ziyao.ideal.web;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.ziyao.ideal.core.SensitiveType;
import com.ziyao.ideal.core.SensitiveUtils;

import java.io.IOException;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class SensitiveSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private SensitiveType sensitiveType;

    @Override
    public void serialize(String source, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        if (sensitiveType == null) {
            jsonGenerator.writeString(source);
        } else
            jsonGenerator.writeString(SensitiveUtils.masking(source, sensitiveType));

    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider,
                                              BeanProperty beanProperty) throws JsonMappingException {
        Sensitive sensitive = beanProperty.getAnnotation(Sensitive.class);
        if (sensitive != null) {
            this.sensitiveType = sensitive.value();
            return this;
        }
        return serializerProvider.findValueSerializer(beanProperty.getType());
    }
}
