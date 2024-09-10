package com.ziyao.ideal.gateway.security.provider;

import com.ziyao.ideal.gateway.security.Authorization;
import com.ziyao.ideal.gateway.security.SessionContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Component
public class OAuth2AuthorizationProvider implements AuthorizationProvider {


    @Override
    public Mono<Authorization> authorize(SessionContext securityContext) {
        return null;
    }
}
