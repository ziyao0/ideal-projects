package com.ziyao.ideal.gateway.security.provider;

import com.ziyao.ideal.gateway.security.Authorization;
import com.ziyao.ideal.gateway.security.GSecurityContext;
import reactor.core.publisher.Mono;

/**
 * @author ziyao zhang
 */
public interface AuthorizationProvider {

    /**
     * 授权处理
     *
     * @param securityContext {@link GSecurityContext}上下文信息
     * @return 返回认证结果
     */
    Mono<Authorization> authorize(GSecurityContext securityContext);
}
