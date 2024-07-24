package com.ziyao.ideal.data.redis.core.repository;

import com.ziyao.ideal.data.redis.core.RedisRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

/**
 * @author ziyao
 */
@NoRepositoryBean
public interface RedisValueRepository<T, ID> extends RedisRepository<T, ID> {

    /**
     * 通过key获取对象
     */
    Optional<T> findById(Object id);

    /**
     * 查询所有
     */
    List<T> findAll();

    /**
     * 保存
     */
    void save(T entity);

    /**
     * @return 如果返回 {@code true} 则表示不存在并保存成功，反则证明存在该key
     */
    boolean saveIfAbsent(T entity);
}
