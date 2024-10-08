package com.ziyao.ideal.gateway.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.config.SystemConfig;
import com.ziyao.ideal.gateway.core.cache.RedisOpsService;
import com.ziyao.ideal.gateway.service.PrincipalCacheService;
import com.ziyao.ideal.security.core.User;
import jakarta.annotation.PostConstruct;
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
public class PrincipalCacheServiceImpl implements PrincipalCacheService {

    private Cache<String, User> principalCache = Caffeine.newBuilder()
            .maximumSize(5000)
            .expireAfterWrite(Duration.ofMinutes(30))
            .build();

    private final RedisOpsService redisOpsService;
    private final ConfigCenter configCenter;

    @Override
    public Optional<User> load(String token) {
        Optional<User> userOptional = Optional.ofNullable(principalCache.getIfPresent(token));
        if (userOptional.isPresent()) {
            return userOptional;
        }

        Optional<User> sessionUserOptional = redisOpsService.findById(token, User.class);
        if (sessionUserOptional.isPresent()) {
            principalCache.put(token, sessionUserOptional.get());
            return sessionUserOptional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> loadAndRefresh(String token) {
        Optional<User> userOptional = load(token);
        userOptional.ifPresent(user -> redisOpsService.expire(token, user.getTtl()));
        return userOptional;
    }

    @Override
    public void save(String token, User user) {
        principalCache.put(token, user);
        redisOpsService.save(token, user);
    }

    @Override
    public void remove(String sessionId) {
        principalCache.invalidate(sessionId);
        redisOpsService.delete(sessionId);
    }

    @Override
    public void refresh(String token, long ttl) {
        redisOpsService.expire(token, ttl);
    }

    @PostConstruct
    @Override
    public void initialize() {
        SystemConfig systemConfig = configCenter.getSystemConfig();
        principalCache = Caffeine.newBuilder()
                .maximumSize(systemConfig.getMaximumSize())
                .expireAfterWrite(Duration.ofMinutes(systemConfig.getSessionTimeout()))
                .build();
    }
}
