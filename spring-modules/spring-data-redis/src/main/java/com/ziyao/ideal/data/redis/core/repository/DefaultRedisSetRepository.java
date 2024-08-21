package com.ziyao.ideal.data.redis.core.repository;

import com.ziyao.ideal.data.redis.core.RedisAdapter;
import com.ziyao.ideal.data.redis.core.RepositoryInformation;
import org.springframework.data.redis.core.RedisCallback;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * @author ziyao
 */
@SuppressWarnings("deprecation")
public class DefaultRedisSetRepository<T, ID>
        extends AbstractRedisRepository<T, ID> implements RedisSetRepository<T, ID> {


    public DefaultRedisSetRepository(RepositoryInformation repositoryInformation,
                                     RedisAdapter adapter) {
        super(adapter, repositoryInformation);

    }

    @Override
    public Set<T> findById(ID id) {

        return adapter.execute(connection -> {
            byte[] rawKey = createKey(id);

            Set<byte[]> rawValues = connection.sMembers(rawKey);
            return conversionProvider.read(metadata.getJavaType(), rawValues);
        });
    }

    @Override
    public void save(Object id, T entity) {
        adapter.execute((RedisCallback<Void>) connection -> {
            byte[] rawKey = createKey(id);
            byte[] rawValue = conversionProvider.write(entity);
            connection.sAdd(rawKey, rawValue);
            if (expires(ttl)) {
                connection.expire(rawKey, ttl);
            }
            return null;
        });
    }

    @Override
    public void saveAll(Object id, Collection<T> entities) {

        Optional<T> optional = entities.stream().findFirst();

        if (!optional.isPresent()) {
            return;
        }

        adapter.execute((RedisCallback<Void>) connection -> {
            byte[] rawKey = createKey(id);
            byte[][] rawValues = conversionProvider.write(entities);
            connection.sAdd(rawKey, rawValues);

            if (expires(ttl)) {
                connection.expire(rawKey, ttl);
            }
            return null;
        });
    }
}
