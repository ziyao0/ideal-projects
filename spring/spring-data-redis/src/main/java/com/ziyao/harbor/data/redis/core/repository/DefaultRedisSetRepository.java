package com.ziyao.harbor.data.redis.core.repository;

import com.ziyao.harbor.data.redis.core.RedisAdapter;
import com.ziyao.harbor.data.redis.core.RepositoryInformation;
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
    public void save(T entity) {

        ID id = this.metadata.getRequiredId(entity);

        adapter.execute((RedisCallback<Void>) connection -> {
            byte[] rawKey = createKey(id);
            byte[] rawValue = conversionProvider.write(entity);
            connection.sAdd(rawKey, rawValue);
            return null;
        });
    }

    @Override
    public void saveAll(Collection<T> entities) {

        Optional<T> optional = entities.stream().findFirst();

        if (optional.isEmpty()) {
            return;
        }
        T entity = optional.get();

        ID id = this.metadata.getRequiredId(entity);

        adapter.execute((RedisCallback<Void>) connection -> {
            byte[] rawKey = createKey(id);
            byte[][] rawValues = conversionProvider.write(entities);
            connection.sAdd(rawKey, rawValues);
            return null;
        });
    }
}
