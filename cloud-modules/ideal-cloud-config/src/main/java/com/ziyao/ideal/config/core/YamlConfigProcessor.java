package com.ziyao.ideal.config.core;

import com.ziyao.ideal.crypto.Property;
import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.core.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 处理把从远程获取的转为map
 *
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class YamlConfigProcessor implements ConfigProcessor<List<Property>> {

    @Override
    public List<Property> load(String source) {
        if (Strings.isEmpty(source)) {
            return Lists.newArrayList();
        }

        OriginTrackedYamlProcessor yamlProcessor = new OriginTrackedYamlProcessor(source);

        List<Map<String, Object>> dataList = yamlProcessor.load();

        List<Property> propertySources = new ArrayList<>();

        for (Map<String, Object> data : dataList) {
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                propertySources.add(new Property(entry.getKey(), entry.getValue()));
            }
        }

        return propertySources;
    }

    @Override
    public String resolve(List<Property> source) {

        if (Collections.isEmpty(source)) {
            return Strings.EMPTY;
        }

        OriginTrackedYamlProcessor yamlProcessor = new OriginTrackedYamlProcessor(source);

        return yamlProcessor.resolve();
    }

    @Override
    public boolean isSupport(String fileType) {
        return false;
    }
}
