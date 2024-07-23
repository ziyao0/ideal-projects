package com.ziyao.harbor.data.redis.core;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.lang.NonNull;

/**
 * @author ziyao zhang
 *
 */
public interface RedisAdapter {

    <T> T findById(Object id, String keyspace, Class<T> type);

    <T> void save(Object id, String keyspace, T source);

    void delete(Object id, String keyspace);

    <T> void deleteOf(@NonNull String keyspace);

    boolean contains(Object id, String keyspace);

    boolean expire(Object id, String keyspace, long ttl);

    <T> T execute(RedisCallback<T> callback);
}
