package com.ziyao.ideal.uaa.repository.redis;

import com.ziyao.ideal.data.redis.core.repository.RedisValueRepository;
import com.ziyao.ideal.uaa.domain.entity.LoginConfig;
import org.springframework.stereotype.Repository;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Repository
public interface LoginConfigRepositoryRedis extends RedisValueRepository<LoginConfig, String> {
}
