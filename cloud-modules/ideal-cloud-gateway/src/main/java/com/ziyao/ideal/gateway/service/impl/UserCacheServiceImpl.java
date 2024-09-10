package com.ziyao.ideal.gateway.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.ziyao.ideal.gateway.core.cache.RedisOpsService;
import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.config.SystemConfig;
import com.ziyao.ideal.gateway.service.UserCacheService;
import com.ziyao.ideal.security.core.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Service
@RequiredArgsConstructor
public class UserCacheServiceImpl implements UserCacheService {

    private Cache<String, SessionUser> sessionUserCache = Caffeine.newBuilder()
            .maximumSize(5000)
            .expireAfterWrite(Duration.ofMinutes(30))
            .build();

    private final RedisOpsService redisOpsService;
    private final ConfigCenter configCenter;

    @Override
    public Optional<SessionUser> load(String sessionId) {
        Optional<SessionUser> userOptional = Optional.ofNullable(sessionUserCache.getIfPresent(sessionId));
        if (userOptional.isPresent()) {
            return userOptional;
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

    @Override
    public void remove(String sessionId) {
        sessionUserCache.invalidate(sessionId);
        redisOpsService.delete(sessionId);
    }

    @Override
    public void initialize() {
        SystemConfig systemConfig = configCenter.getSystemConfig();
        sessionUserCache = Caffeine.newBuilder()
                .maximumSize(systemConfig.getMaximumSize())
                .expireAfterWrite(Duration.ofMinutes(systemConfig.getSessionTimeout()))
                .build();
    }
}
