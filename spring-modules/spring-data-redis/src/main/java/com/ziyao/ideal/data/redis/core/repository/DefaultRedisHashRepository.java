package com.ziyao.ideal.data.redis.core.repository;

import com.ziyao.ideal.data.redis.core.RedisAdapter;
import com.ziyao.ideal.data.redis.core.RepositoryInformation;

import java.util.Map;
import java.util.Optional;

/**
 * @author ziyao
 */
public class DefaultRedisHashRepository<T, ID>
        extends AbstractRedisRepository<T, ID> implements RedisHashRepository<T, ID> {

    public DefaultRedisHashRepository(RepositoryInformation repositoryInformation, RedisAdapter adapter) {
        super(adapter, repositoryInformation);
    }

    @Override
    public Optional<Map<Object, Object>> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Object> findByHashKey(ID id, Object hashKey) {
        return Optional.empty();
    }

    @Override
    public Long deleteByHashKey(String id, Object... hashKey) {
        return 0L;
    }

    @Override
    public void save(Object hashKey, Object hashValue) {

    }

    @Override
    public void saveAll(Map<Object, Object> map) {

    }
}
