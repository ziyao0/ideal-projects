package com.ziyao.ideal.convert;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.Collection;
import java.util.List;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
final class ObjectConversionProvider implements ConversionProvider {

    private static final String INVALID_TYPE_ASSIGNMENT = "Value of type %s cannot be assigned to property %s of type %s";


    private final ObjectMapper mapper;
    private final GenericConversionService converters;

    public ObjectConversionProvider() {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = this.getClass().getClassLoader();
        List<Module> modules = Jackson2Modules.getModules(classLoader);
        mapper.registerModules(modules);
        this.mapper = mapper;

        this.converters = new DefaultConversionService();

        Collection<Converter<?, ?>> converters = BinaryConverters.getConvertersToRegister();

        converters.addAll(Jsr310Converters.getConvertersToRegister());
        converters.addAll(TimesConverters.getConvertersToRegister());
        converters.add(new BytesToMapConverter());
        converters.add(new MapToBytesConverter());

        for (Converter<?, ?> converter : converters) {
            this.converters.addConverter(converter);
        }
    }

    @Override
    public <T> byte[] write(T source) {
        try {
            if (source instanceof String) {
                return this.converters.convert(source, byte[].class);
            }
            return mapper.writeValueAsBytes(source);
        } catch (Exception e) {
            throw new RuntimeException("Could not serialize: " + e.getMessage(), e);
        }
    }

    @Override
    public <T> T read(Class<T> type, byte[] source) {
        try {
            if (source == null || source.length == 0) {
                return null;
            }
            return mapper.readValue(source, type);
        } catch (Exception e) {
            throw new RuntimeException("Could not serialize: " + e.getMessage(), e);
        }
    }


    @Override
    public <T> T convert(Object source, Class<T> type) {

        if (this.converters.canConvert(type, source.getClass())) {

            return this.converters.convert(source, type);
        }
        throw new IllegalArgumentException(source + "不支持转换为：" + type.getName());
    }


    @Override
    public void initializeConverters() {

    }

}
