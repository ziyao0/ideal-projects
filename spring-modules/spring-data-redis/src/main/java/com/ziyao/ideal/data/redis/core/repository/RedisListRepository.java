package com.ziyao.ideal.data.redis.core.repository;

import com.ziyao.ideal.data.redis.core.RedisRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author ziyao
 */
public interface RedisListRepository<T, ID> extends RedisRepository<T, ID> {


    List<T> findById(ID id);

    void save(T value);

    void saveAll(List<T> values);

    Optional<T> leftPop(ID id);

    Optional<T> rightPop(ID id);

    void leftPush(T value);

    void rightPush(T value);
}
