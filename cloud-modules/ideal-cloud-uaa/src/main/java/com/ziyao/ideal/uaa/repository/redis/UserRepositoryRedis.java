package com.ziyao.ideal.uaa.repository.redis;

import com.ziyao.ideal.data.redis.core.repository.RedisValueRepository;
import com.ziyao.ideal.security.core.User;
import org.springframework.stereotype.Repository;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Repository
public interface UserRepositoryRedis extends RedisValueRepository<User, String> {
}
