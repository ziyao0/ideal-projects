package com.ziyao.ideal.gateway.authorization;

import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.core.SecurityPredicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Component
@RequiredArgsConstructor
public class DefaultAuthorizationProvider implements AuthorizationProvider {

    private final ConfigCenter configCenter;

    @Override
    public Authorization authorize(Authorization authorization) {

        DefaultAuthorizationToken authorizationToken = (DefaultAuthorizationToken) authorization;

        boolean skip = SecurityPredicate.initSecurityApis(getSecurityApis()).skip(authorizationToken.getRequestPath());
        return AuthorizationToken.of();
    }

    @Override
    public boolean supports(Class<? extends Authorization> authorizationClass) {
        return DefaultAuthorizationToken.class.isAssignableFrom(authorizationClass);
    }


    private Set<String> getSecurityApis() {
        Set<String> skipApis = configCenter.getGatewayConfig().getDefaultSkipApis();
        skipApis.addAll(configCenter.getGatewayConfig().getSkipApis());
        return skipApis;
    }
}
