package com.ziyao.config.core;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public enum ConfigType {

    YAML("yaml"),
    PROPERTIES("properties"),
    JSON("json"),
    XML("xml"),
    TEXT("text"),
    HTML("html"),
    TOML("toml"),
    ;


    private final String type;

    ConfigType(String type) {
        this.type = type;
    }

    public String type() {
        return this.type;
    }
}
