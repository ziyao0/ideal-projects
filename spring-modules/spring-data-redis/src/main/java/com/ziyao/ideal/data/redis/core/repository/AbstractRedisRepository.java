package com.ziyao.ideal.data.redis.core.repository;

import com.ziyao.ideal.data.redis.core.*;
import com.ziyao.ideal.data.redis.core.convert.ConversionProvider;
import org.springframework.data.keyvalue.annotation.KeySpace;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.mapping.RedisMappingContext;
import org.springframework.data.redis.core.mapping.RedisPersistentEntity;

import java.util.concurrent.TimeUnit;

/**
 * @author ziyao
 */

abstract class AbstractRedisRepository<T, ID> implements RedisRepository<T, ID> {

    final RedisAdapter adapter;
    final RedisEntityInformation<T, ID> metadata;
    final ConversionProvider conversionProvider = ConversionProvider.getInstance();
    final String keyspace;
    long ttl = -1;

    @SuppressWarnings("unchecked")
    public AbstractRedisRepository(RedisAdapter adapter, RepositoryInformation repositoryInformation) {
        this.adapter = adapter;
        RedisPersistentEntity<?> persistentEntity = new RedisMappingContext().getPersistentEntity(repositoryInformation.getJavaType());
        this.metadata = (RedisEntityInformation<T, ID>) new RedisEntityInformation<>(persistentEntity);
        String keySpace = this.metadata.getKeySpace();
        if (keySpace == null) {
            KeySpace annotation = repositoryInformation.getJavaType().getAnnotation(KeySpace.class);
            if (annotation != null) {
                keySpace = annotation.value();
            }
        }
        this.keyspace = keySpace;
        TimeToLive annotation = repositoryInformation.getJavaType().getAnnotation(TimeToLive.class);
        if (annotation != null) {
            this.ttl = TimeoutUtils.toMillis(annotation.ttl(), annotation.unit());
        }
    }

    @Override
    public boolean exists(ID id) {
        return adapter.contains(id, resolveKeySpace());
    }

    @Override
    public void delete(ID id) {
        adapter.delete(id, resolveKeySpace());
    }

    @Override
    public boolean expire(ID id, long timeout, TimeUnit timeUnit) {
        return adapter.expire(id, resolveKeySpace(), timeout);
    }

    @Override
    public <R> R execute(RedisCallback<R> redisCallback) {
        return adapter.execute(redisCallback);
    }

    protected String resolveKeySpace() {
        return this.metadata.getKeySpace();
    }

    protected byte[] createKey(Object id) {
        return conversionProvider.write(resolveKeySpace() + ":" + conversionProvider.convert(id, String.class));
    }

    protected boolean expires(Long timeToLive) {
        return timeToLive != null && timeToLive > 0;
    }

}
