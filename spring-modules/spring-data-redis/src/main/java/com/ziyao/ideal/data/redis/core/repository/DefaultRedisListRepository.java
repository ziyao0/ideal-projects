package com.ziyao.ideal.data.redis.core.repository;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.data.redis.core.RedisAdapter;
import com.ziyao.ideal.data.redis.core.RepositoryInformation;
import org.springframework.data.redis.core.RedisCallback;

import java.util.List;
import java.util.Optional;

/**
 * @author ziyao
 */
@SuppressWarnings("deprecation")
public class DefaultRedisListRepository<T, ID>
        extends AbstractRedisRepository<T, ID> implements RedisListRepository<T, ID> {


    public DefaultRedisListRepository(RepositoryInformation repositoryInformation,
                                      RedisAdapter redisAdapter) {
        super(redisAdapter, repositoryInformation);
    }

    @Override
    public List<T> findById(ID id) {

        return adapter.execute(connection -> {

            byte[] rawKey = createKey(id);

            List<byte[]> raws = connection.lRange(rawKey, 0, -1);
            if (Collections.isEmpty(raws)) {
                return Lists.newArrayList();
            }
            return conversionProvider.read(this.metadata.getJavaType(), raws);
        });
    }

    @Override
    public void save(Object id, T entity) {

        adapter.execute((RedisCallback<Void>) connection -> {

            byte[] rawKey = createKey(id);
            byte[] rawValue = conversionProvider.write(entity);

            connection.rPush(rawKey, rawValue);
            if (expires(ttl)) {
                connection.expire(rawKey, ttl);
            }
            return null;
        });
    }

    @Override
    public void saveAll(Object id, List<T> values) {

        adapter.execute((RedisCallback<Void>) connection -> {

            byte[] rawKey = createKey(id);
            byte[][] rawValues = conversionProvider.write(values);

            connection.rPush(rawKey, rawValues);

            if (expires(ttl)) {
                connection.expire(rawKey, ttl);
            }
            return null;
        });
    }

    @Override
    public Optional<T> leftPop(ID id) {

        RedisCallback<Optional<T>> command = connection -> {
            byte[] rawKey = createKey(id);
            byte[] rawValue = connection.lPop(rawKey);
            return Optional.ofNullable(conversionProvider.read(metadata.getJavaType(), rawValue));
        };

        return adapter.execute(command);
    }

    @Override
    public Optional<T> rightPop(ID id) {
        RedisCallback<Optional<T>> command = connection -> {
            byte[] rawKey = createKey(id);
            byte[] rawValue = connection.rPop(rawKey);
            return Optional.ofNullable(conversionProvider.read(metadata.getJavaType(), rawValue));
        };

        return adapter.execute(command);
    }

    @Override
    public void leftPush(Object id, T entity) {

        RedisCallback<Void> command = connection -> {
            byte[] rawKey = createKey(id);
            byte[] rawValue = conversionProvider.write(entity);

            connection.lPush(rawKey, rawValue);
            if (expires(ttl)) {
                connection.expire(rawKey, ttl);
            }
            return null;
        };

        adapter.execute(command);
    }

    @Override
    public void rightPush(Object id, T entity) {

        RedisCallback<Void> command = connection -> {
            byte[] rawKey = createKey(id);
            byte[] rawValue = conversionProvider.write(entity);

            connection.rPush(rawKey, rawValue);
            if (expires(ttl)) {
                connection.expire(rawKey, ttl);
            }
            return null;
        };

        adapter.execute(command);
    }
}
