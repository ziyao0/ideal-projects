package com.ziyao.ideal.uaa.repository.redis;

import com.ziyao.ideal.data.redis.core.repository.RedisValueRepository;
import com.ziyao.ideal.uaa.domain.entity.Authorization;
import org.springframework.stereotype.Repository;

/**
 * @author ziyao
 */
@Repository
public interface AuthorizationRepositoryRedis extends RedisValueRepository<Authorization, Integer> {
}
