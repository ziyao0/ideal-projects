package com.ziyao.harbor.usercenter.repository.redis;

import com.ziyao.harbor.data.redis.core.repository.RedisValueRepository;
import com.ziyao.harbor.usercenter.entity.MenuTree;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ziyao
 */
@Repository
public interface MenuTreeRepositoryRedis extends RedisValueRepository<List<MenuTree>, String> {
}
