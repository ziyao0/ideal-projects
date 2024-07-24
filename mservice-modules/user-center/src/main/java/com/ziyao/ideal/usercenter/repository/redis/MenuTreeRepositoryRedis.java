package com.ziyao.ideal.usercenter.repository.redis;

import com.ziyao.ideal.data.redis.core.repository.RedisValueRepository;
import com.ziyao.ideal.usercenter.entity.MenuTree;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ziyao
 */
@Repository
public interface MenuTreeRepositoryRedis extends RedisValueRepository<List<MenuTree>, String> {
}
