package com.ziyao.ideal.data.redis.core.repository;

import com.ziyao.ideal.data.redis.core.RedisAdapter;
import com.ziyao.ideal.data.redis.core.RedisEntityInformation;
import com.ziyao.ideal.data.redis.core.RedisRepository;
import com.ziyao.ideal.data.redis.core.RepositoryInformation;
import com.ziyao.ideal.data.redis.core.convert.ConversionProvider;
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

    @SuppressWarnings("unchecked")
    public AbstractRedisRepository(RedisAdapter adapter, RepositoryInformation repositoryInformation) {
        this.adapter = adapter;

        RedisPersistentEntity<?> persistentEntity = new RedisMappingContext().getPersistentEntity(repositoryInformation.getJavaType());

        this.metadata = (RedisEntityInformation<T, ID>) new RedisEntityInformation<>(persistentEntity);

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
