package com.ziyao.config.core;

import com.ziyao.crypto.Property;

import java.util.List;
import java.util.Map;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class YamlToPropertiesProcessor implements ConfigProcessor<Map<Object, Object>, List<Property>> {

    @Override
    public Map<Object, Object> process(List<Property> source) {
        return Map.of();
    }

    @Override
    public boolean isSupport(String fileType) {
        return (fileType.endsWith(".yaml") || fileType.endsWith(".yml"));
    }
}
