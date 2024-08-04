package com.ziyao.ideal.data.redis.core.repository;

import com.ziyao.ideal.data.redis.core.RedisRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author ziyao
 */
public interface RedisHashRepository<K, V> extends RedisRepository<Object, Object> {

    /**
     * 通过ID 获取存储的hash数据
     */
    Map<K, V> findById(Object id);

    /**
     * 通过hash key获取数据
     *
     * @param id  redis key
     * @param key hash 键
     * @return 返回 hash 值
     */
    Optional<V> findByKey(Object id, Object key);

    /**
     * Get key set (fields) of hash at key.
     * <p>
     * Params:
     * key – must not be null.
     * Returns:
     * null when used in pipeline / transaction.
     */
    Set<K> keys(Object id);

    /**
     * Get entry set (values) of hash at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    List<V> values(Object key);

    /**
     * 通过hk删除
     */
    Long deleteByKeys(Object id, Object... keys);

    /**
     * 保存
     */
    void save(Object id, K key, V value);

    /**
     * 保存全部
     */
    void saveAll(Object id, Map<K, V> map);
}
