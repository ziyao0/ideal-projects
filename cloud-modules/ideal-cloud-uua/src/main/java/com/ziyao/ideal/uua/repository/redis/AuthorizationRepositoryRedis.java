package com.ziyao.ideal.uua.repository.redis;

import com.ziyao.ideal.data.redis.core.repository.RedisValueRepository;
import com.ziyao.ideal.uua.domain.entity.Authorization;
import org.springframework.stereotype.Repository;

/**
 * @author ziyao
 */
@Repository
public interface AuthorizationRepositoryRedis extends RedisValueRepository<Authorization, Integer> {
}
