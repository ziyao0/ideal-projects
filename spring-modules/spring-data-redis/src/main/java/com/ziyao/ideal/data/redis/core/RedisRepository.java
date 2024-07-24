package com.ziyao.ideal.data.redis.core;

import org.springframework.data.redis.core.RedisCallback;

import java.util.concurrent.TimeUnit;

/**
 * @author ziyao zhang
 *
 */
public interface RedisRepository<T, ID> {

    /**
     * 判断缓存中有没有这个key
     */
    boolean exists(ID id);

    /**
     * 删除key
     */
    void delete(ID id);

    /**
     * 刷新过期时间
     */
    boolean expire(ID id, long timeout, TimeUnit timeUnit);

    <R> R execute(RedisCallback<R> redisCallback);
}
