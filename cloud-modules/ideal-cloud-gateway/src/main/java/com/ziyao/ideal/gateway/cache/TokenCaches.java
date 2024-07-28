package com.ziyao.ideal.gateway.cache;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author ziyao
 */
public abstract class TokenCaches {
    private static final Set<String> token_cache = new ConcurrentSkipListSet<>();


    public static void offline(String token) {

    }

    public static void online(String token) {

    }


    private TokenCaches() {
    }
}
