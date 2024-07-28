package com.ziyao.ideal.uua.repository.redis;

import com.ziyao.ideal.data.redis.core.repository.RedisValueRepository;
import com.ziyao.ideal.uua.domain.entity.MenuTree;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ziyao
 */
@Repository
public interface MenuTreeRepositoryRedis extends RedisValueRepository<List<MenuTree>, String> {
}
