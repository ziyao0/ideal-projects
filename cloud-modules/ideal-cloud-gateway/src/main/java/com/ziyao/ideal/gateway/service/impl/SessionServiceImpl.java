package com.ziyao.ideal.gateway.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.ziyao.ideal.gateway.cache.RedisOpsService;
import com.ziyao.ideal.gateway.service.SessionService;
import com.ziyao.ideal.security.core.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final Cache<String, SessionUser> sessionUserCache =
            Caffeine.newBuilder()
                    .maximumSize(5000)
                    .expireAfterWrite(7, TimeUnit.DAYS)
                    .build();

    private final RedisOpsService redisOpsService;

    @Override
    public Optional<SessionUser> load(String sessionId) {
        SessionUser sessionUser = sessionUserCache.getIfPresent(sessionId);
        if (Optional.ofNullable(sessionUser).isPresent()) {
            return Optional.of(sessionUser);
        }
        Optional<SessionUser> sessionUserOptional = redisOpsService.findById(sessionId, SessionUser.class);
        if (sessionUserOptional.isPresent()) {
            sessionUserCache.put(sessionId, sessionUserOptional.get());
            return sessionUserOptional;
        }
        return Optional.empty();
    }

    @Override
    public void save(String sessionId, SessionUser sessionUser) {
        sessionUserCache.put(sessionId, sessionUser);
        redisOpsService.save(sessionId, sessionUser);
    }
}
