package com.ziyao.ideal.gateway.service.impl;

import com.ziyao.ideal.gateway.service.SecurityService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author ziyao
 */
@Slf4j
@Service
public class SecurityServiceImpl implements SecurityService {

    @Resource
    private ReactiveStringRedisTemplate reactiveStringRedisTemplate;

    @Override
    public void offline(String token) {
        reactiveStringRedisTemplate.delete(token).subscribe(
                size -> log.debug("退出成功！")
        );
    }
}
