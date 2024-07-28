package com.ziyao.ideal.uua.repository.redis;

import com.ziyao.ideal.data.redis.core.repository.RedisValueRepository;
import com.ziyao.ideal.uua.domain.entity.Application;
import org.springframework.stereotype.Repository;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Repository
public interface ApplicationRepositoryRedis extends RedisValueRepository<Application, Long> {
}
