package com.ziyao.ideal.gateway.support;

/**
 * @author ziyao
 */
public abstract class RedisKeys {

    private static final String DEBOUNCE_PR = "ziyao:gateway:debounce:";
    public static final String PERMISSION_REDIS_KEY = "sys:permission:request:";

    public static String getDebounceKeyByValue(String value) {
        return DEBOUNCE_PR + value;
    }

    public static String getPermissionKeyById(Integer userId) {
        return PERMISSION_REDIS_KEY + userId;
    }

    private RedisKeys() {
    }
}
