package com.ziyao.ideal.gateway.service.impl;

import com.ziyao.ideal.gateway.authorization.AuthorizationToken;
import com.ziyao.ideal.gateway.service.PermissionService;
import com.ziyao.ideal.gateway.support.RedisKeys;
import com.ziyao.ideal.security.core.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author ziyao zhang
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final ReactiveStringRedisTemplate redisTemplate;

    @Override
    public Mono<AuthorizationToken> verify(AuthorizationToken authorizationToken) {

        String requestPath = authorizationToken.getRequestPath();
        Optional<User> principal = authorizationToken.getPrincipal();

        AuthorizationToken.Builder builder = AuthorizationToken.form(authorizationToken);

        if (principal.isEmpty()) {
            return Mono.just(builder.build());
        }
        String permissionKey = RedisKeys.getPermissionKeyById(principal.get().getId());
        return redisTemplate.opsForSet().isMember(permissionKey, requestPath)
                .map(result -> Boolean.TRUE.equals(result)
                        ? builder.authorized().build()
                        : builder.response(HttpStatus.FORBIDDEN).build());
    }
}
