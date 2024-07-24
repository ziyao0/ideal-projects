package com.ziyao.ideal.gateway.core;

import com.ziyao.ideal.gateway.core.token.AccessToken;
import com.ziyao.ideal.gateway.core.token.Authorization;
import reactor.core.publisher.Mono;

/**
 * @author ziyao zhang
 */
public interface Authorizer {

    /**
     * 授权处理
     *
     * @param accessToken {@link AccessToken}授权核心参数
     * @return 返回认证结果
     */
    Mono<Authorization> authorize(AccessToken accessToken);
}
