package com.ziyao.ideal.data.redis.core.repository;

import com.ziyao.ideal.data.redis.core.*;
import com.ziyao.ideal.data.redis.core.convert.ConversionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.keyvalue.annotation.KeySpace;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.mapping.RedisMappingContext;
import org.springframework.data.redis.core.mapping.RedisPersistentEntity;

import java.util.concurrent.TimeUnit;

/**
 * @author ziyao
 */

abstract class AbstractRedisRepository<T, ID> implements RedisRepository<T, ID> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractRedisRepository.class);

    long ttl = -1;
    String keyspace;
    final RedisAdapter adapter;
    RedisEntityInformation<T, ID> metadata;
    final ConversionProvider conversionProvider = ConversionProvider.getInstance();

    @SuppressWarnings("unchecked")
    public AbstractRedisRepository(RedisAdapter adapter, RepositoryInformation repositoryInformation) {
        this.adapter = adapter;

        // 获取key存活时间
        TimeToLive timeToLiveAnn = repositoryInformation.getJavaType().getAnnotation(TimeToLive.class);
        if (timeToLiveAnn != null) {
            this.ttl = TimeoutUtils.toMillis(timeToLiveAnn.ttl(), timeToLiveAnn.unit());
        }

        KeySpace keySpaceAnn = repositoryInformation.getJavaType().getAnnotation(KeySpace.class);
        if (keySpaceAnn != null) {
            this.keyspace = keySpaceAnn.value();
        }
        try {
            RedisPersistentEntity<?> persistentEntity = new RedisMappingContext().getPersistentEntity(repositoryInformation.getJavaType());

            this.metadata = (RedisEntityInformation<T, ID>) new RedisEntityInformation<>(persistentEntity);

            String keySpace = this.metadata.getKeySpace();
            if (keySpace != null) {
                this.keyspace = keySpace;
            }

        } catch (Exception e) {
            logger.warn("Can not get keyspace annotation", e);
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
        return this.keyspace == null ? "" : this.keyspace;
    }

    protected byte[] createKey(Object id) {
        return conversionProvider.write(resolveKeySpace() + ":" + conversionProvider.convert(id, String.class));
    }

    protected boolean expires(Long timeToLive) {
        return timeToLive != null && timeToLive > 0;
    }
}
