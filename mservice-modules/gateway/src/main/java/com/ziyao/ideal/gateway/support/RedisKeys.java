package com.ziyao.ideal.gateway.support;

/**
 * @author ziyao
 */
public abstract class RedisKeys {

    private static final String DEBOUNCE_PR = "ziyao:gateway:debounce:";

    public static String getDebounceKeyByValue(String value) {
        return DEBOUNCE_PR + value;
    }

    private RedisKeys() {
    }
}
