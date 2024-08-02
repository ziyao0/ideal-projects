package com.ziyao.ideal.uua.repository.redis;

import com.ziyao.ideal.data.redis.core.repository.RedisValueRepository;
import com.ziyao.ideal.security.core.context.SecurityContextImpl;
import org.springframework.data.keyvalue.annotation.KeySpace;
import org.springframework.stereotype.Repository;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Repository
@KeySpace("")
public interface SecurityContextRepositoryRedis extends RedisValueRepository<SecurityContextImpl, String> {
}
