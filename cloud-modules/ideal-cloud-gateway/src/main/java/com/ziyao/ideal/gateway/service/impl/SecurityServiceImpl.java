package com.ziyao.ideal.gateway.service.impl;

import com.ziyao.ideal.gateway.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author ziyao
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final ReactiveStringRedisTemplate reactiveStringRedisTemplate;

    @Override
    public void offline(String token) {
        reactiveStringRedisTemplate.delete(token).subscribe(
                size -> log.debug("退出成功！")
        );
    }
}
