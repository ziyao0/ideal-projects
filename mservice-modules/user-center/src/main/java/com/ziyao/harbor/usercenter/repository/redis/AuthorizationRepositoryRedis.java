package com.ziyao.harbor.usercenter.repository.redis;

import com.ziyao.harbor.data.redis.core.repository.RedisValueRepository;
import com.ziyao.harbor.usercenter.entity.Authorization;
import org.springframework.stereotype.Repository;

/**
 * @author ziyao
 */
@Repository
public interface AuthorizationRepositoryRedis extends RedisValueRepository<Authorization, Long> {
}
