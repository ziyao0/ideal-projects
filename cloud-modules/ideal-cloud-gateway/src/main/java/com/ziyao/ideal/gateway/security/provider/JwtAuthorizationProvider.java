package com.ziyao.ideal.gateway.security.provider;

import com.ziyao.ideal.gateway.security.Authorization;
import com.ziyao.ideal.gateway.security.GSecurityContext;
import reactor.core.publisher.Mono;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class JwtAuthorizationProvider implements AuthorizationProvider {

    @Override
    public Mono<Authorization> authorize(GSecurityContext securityContext) {
        return null;
    }
}
