package com.ziyao.ideal.data.redis.core.repository;

import com.google.common.collect.Lists;
import com.ziyao.ideal.data.redis.core.RedisAdapter;
import com.ziyao.ideal.data.redis.core.RepositoryInformation;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.mapping.RedisPersistentEntity;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author ziyao zhang
 */
@SuppressWarnings("deprecation")
public class DefaultRedisValueRepository<T, ID>
        extends AbstractRedisRepository<T, ID> implements RedisValueRepository<T, ID> {


    public DefaultRedisValueRepository(RepositoryInformation repositoryInformation,
                                       RedisAdapter redisAdapter) {
        super(redisAdapter, repositoryInformation);
    }

    @Override
    public Optional<T> findById(@NonNull Object id) {
        String keySpace = this.resolveKeySpace();
        Class<T> javaType = this.metadata.getJavaType();
        return Optional.ofNullable(adapter.findById(id, keySpace, javaType));
    }

    @Override
    public List<T> findAll() {
        return Lists.newArrayList();
    }

    @Override
    public void save(T source) {
        RedisPersistentEntity<?> entity = this.conversionProvider
                .getMappingContext().getRequiredPersistentEntity(source.getClass());

        String keySpace = this.resolveKeySpace();
        Object id = entity.getIdentifierAccessor(source).getIdentifier();
        adapter.save(id, keySpace, entity);
    }


    @Override
    public boolean saveIfAbsent(T source) {

        RedisPersistentEntity<?> entity = this.conversionProvider
                .getMappingContext().getRequiredPersistentEntity(source.getClass());

        Object id = entity.getIdentifierAccessor(source).getIdentifier();


        return this.execute(connection -> {

            byte[] rawKey = createKey(id);
            byte[] rawValue = conversionProvider.write(source);

            Long ttl = entity.getTimeToLiveAccessor().getTimeToLive(source);
            if (ttl != null && ttl > 0) {

                Expiration expiration = Expiration.from(ttl, TimeUnit.SECONDS);
                return connection.set(rawKey, rawValue, expiration, RedisStringCommands.SetOption.ifAbsent());
            } else {

                return connection.setNX(rawKey, rawValue);
            }

        });
    }

    @Override
    public void save(Object id, T entity) {
        adapter.execute((RedisCallback<Void>) connection -> {
            byte[] rawKey = createKey(id);
            byte[] rawValue = conversionProvider.write(entity);


            if (expires(this.ttl)) {
                connection.expire(rawKey, this.ttl);
            } else {
                connection.set(rawKey, rawValue);
            }
            return null;
        });
    }

    @Override
    public boolean saveIfAbsent(Object id, T entity) {

        return this.execute(connection -> {
            byte[] rawKey = createKey(id);
            byte[] rawValue = conversionProvider.write(entity);

            if (expires(ttl)) {
                Expiration expiration = Expiration.from(ttl, TimeUnit.SECONDS);
                return connection.set(rawKey, rawValue, expiration, RedisStringCommands.SetOption.ifAbsent());
            } else {
                return connection.setNX(rawKey, rawValue);
            }
        });
    }

}
