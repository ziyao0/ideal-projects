package com.ziyao.ideal.data.redis.core.repository;

import com.ziyao.ideal.data.redis.core.RedisRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ziyao
 */
public interface RedisListRepository<T, ID> extends RedisRepository<T, ID> {


    List<T> findById(ID id);

    void save(Object id, T value);

    void saveAll(Object id, List<T> values);

    Optional<T> leftPop(ID id);

    Optional<T> rightPop(ID id);

    void leftPush(Object id, T value);

    void rightPush(Object id, T value);
}
