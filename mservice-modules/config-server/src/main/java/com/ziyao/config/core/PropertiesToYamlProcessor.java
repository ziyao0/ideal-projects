package com.ziyao.config.core;

import com.ziyao.crypto.Property;

import java.util.List;
import java.util.Map;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class PropertiesToYamlProcessor implements ConfigProcessor<List<Property>, Map<Object, Object>> {
    @Override
    public List<Property> process(Map<Object, Object> source) {
        return List.of();
    }

    @Override
    public boolean isSupport(String fileType) {
        return false;
    }
}
