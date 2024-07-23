package com.ziyao.config.core;

import com.ziyao.eis.core.Strings;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public enum YamlPropertiesSupport {


    YAML("yaml"),
    YML("yml"),
    PROPERTIES("properties");

    private final String type;

    YamlPropertiesSupport(String type) {
        this.type = type;
    }

    private static final Map<String, YamlPropertiesSupport> MAP;

    static {
        MAP = Arrays.stream(values()).collect(Collectors.toMap(YamlPropertiesSupport::getType, Function.identity()));
    }

    public static YamlPropertiesSupport get(String type) {
        if (Strings.hasText(type)) {
            return MAP.get(type);
        }
        return null;
    }
}
