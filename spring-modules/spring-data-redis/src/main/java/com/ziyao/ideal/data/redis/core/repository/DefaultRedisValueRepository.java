package com.ziyao.ideal.data.redis.core.repository;

import com.ziyao.ideal.data.redis.core.RedisAdapter;
import com.ziyao.ideal.data.redis.core.RepositoryInformation;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.mapping.RedisPersistentEntity;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author ziyao zhang
 */
public class DefaultRedisValueRepository<T, ID>
        extends AbstractRedisRepository<T, ID> implements RedisValueRepository<T, ID> {


    public DefaultRedisValueRepository(RepositoryInformation repositoryInformation,
                                       RedisAdapter redisAdapter) {
        super(redisAdapter, repositoryInformation);

    }

    @Override
    public Optional<T> findById(@NonNull Object id) {
        String keySpace = this.metadata.getKeySpace();
        Class<T> javaType = this.metadata.getJavaType();
        return Optional.ofNullable(adapter.findById(id, keySpace, javaType));
    }

    @Override
    public List<T> findAll() {
        return List.of();
    }

    @Override
    public void save(T source) {
        RedisPersistentEntity<?> entity = this.conversionProvider
                .getMappingContext().getRequiredPersistentEntity(source.getClass());

        String keySpace = entity.getKeySpace();
        Object id = entity.getIdentifierAccessor(source).getIdentifier();
        adapter.save(id, keySpace, entity);
    }


    @Override
    @SuppressWarnings("deprecation")
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

}
