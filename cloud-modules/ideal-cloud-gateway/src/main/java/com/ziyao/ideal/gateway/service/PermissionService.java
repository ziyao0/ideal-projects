package com.ziyao.ideal.gateway.service;

import com.ziyao.ideal.gateway.authorization.AuthorizationToken;
import reactor.core.publisher.Mono;

/**
 * @author ziyao zhang
 */
public interface PermissionService {

    /**
     * 验证用户权限是否允许访问当前当前资源
     *
     * @param authorizationToken 权限验证基本信息
     * @return {@link AuthorizationToken}
     */
    Mono<AuthorizationToken> verify(AuthorizationToken authorizationToken);
}
