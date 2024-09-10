package com.ziyao.ideal.gateway.core.cache;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;

/**
 * @author ziyao zhang
 */
public abstract class ReactiveBeans {

    public static ReactiveRedisTemplate<String, Object> reactiveRedis;
    public static ReactiveStringRedisTemplate reactiveStringRedis;

    public static void setRedis(ReactiveRedisTemplate<String, Object> reactiveRedis) {
        ReactiveBeans.reactiveRedis = reactiveRedis;
    }

    public static void setStringRedis(ReactiveStringRedisTemplate reactiveStringRedis) {
        ReactiveBeans.reactiveStringRedis = reactiveStringRedis;
    }
}
