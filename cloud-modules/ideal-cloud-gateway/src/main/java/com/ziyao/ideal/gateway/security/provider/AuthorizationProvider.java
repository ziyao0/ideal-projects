package com.ziyao.ideal.gateway.security.provider;

import com.ziyao.ideal.gateway.security.Authorization;
import com.ziyao.ideal.gateway.security.SessionContext;
import reactor.core.publisher.Mono;

/**
 * @author ziyao zhang
 */
public interface AuthorizationProvider {

    /**
     * 授权处理
     *
     * @param securityContext {@link SessionContext}上下文信息
     * @return 返回认证结果
     */
    Mono<Authorization> authorize(SessionContext securityContext);
}
