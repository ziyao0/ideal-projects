package com.ziyao.ideal.convert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class BytesToMapConverter implements Converter<byte[], Map<String, Object>> {


    public final TypeReference<Map<String, Object>> mapTypeReference = new TypeReference<>() {
    };

    private final ObjectMapper mapper = new ObjectMapper();

    public BytesToMapConverter() {
        ClassLoader classLoader = BytesToMapConverter.class.getClassLoader();
        List<Module> securityModules = Jackson2Modules.getModules(classLoader);
        this.mapper.registerModules(securityModules);
    }

    @Override
    public Map<String, Object> convert(byte[] source) {

        if (source.length == 0) {
            return Map.of();
        }
        try {
            return mapper.readValue(source, mapTypeReference);
        } catch (IOException e) {
            throw new RuntimeException("bytes to map error " + e.getMessage(), e);
        }
    }
}
