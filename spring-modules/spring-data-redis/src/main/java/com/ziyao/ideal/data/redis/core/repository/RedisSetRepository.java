package com.ziyao.ideal.data.redis.core.repository;

import com.ziyao.ideal.data.redis.core.RedisRepository;

import java.util.Collection;
import java.util.Set;

/**
 * @author ziyao
 */
public interface RedisSetRepository<T, ID> extends RedisRepository<T, ID> {


    Set<T> findById(ID id);

    void save(T entity);

    void saveAll(Collection<T> entities);
}
