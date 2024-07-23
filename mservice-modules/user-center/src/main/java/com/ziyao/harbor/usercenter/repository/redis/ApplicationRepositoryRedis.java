package com.ziyao.harbor.usercenter.repository.redis;

import com.ziyao.harbor.data.redis.core.repository.RedisValueRepository;
import com.ziyao.harbor.usercenter.entity.Application;
import org.springframework.stereotype.Repository;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Repository
public interface ApplicationRepositoryRedis extends RedisValueRepository<Application, Long> {
}
