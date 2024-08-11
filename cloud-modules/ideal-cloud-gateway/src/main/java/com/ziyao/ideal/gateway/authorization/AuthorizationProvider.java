package com.ziyao.ideal.gateway.authorization;

import com.ziyao.ideal.gateway.authorization.token.AccessToken;
import com.ziyao.ideal.gateway.authorization.token.Authorization;
import reactor.core.publisher.Mono;

/**
 * @author ziyao zhang
 */
public interface AuthorizationProvider {

    /**
     * 授权处理
     *
     * @param accessToken {@link AccessToken}授权核心参数
     * @return 返回认证结果
     */
    Mono<Authorization> authorize(AccessToken accessToken);
}
