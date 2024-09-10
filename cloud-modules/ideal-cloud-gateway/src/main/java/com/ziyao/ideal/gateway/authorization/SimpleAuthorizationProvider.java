package com.ziyao.ideal.gateway.authorization;

import com.ziyao.ideal.gateway.core.SecurityPredicate;
import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.service.UserCacheService;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@RequiredArgsConstructor
public class SimpleAuthorizationProvider implements AuthorizationProvider {

    private final UserCacheService userCacheService;
    private final ConfigCenter configCenter;

    @Override
    public Authorization authorize(Authorization authorization) {

        SimpleAuthorizationToken authorizationToken = (SimpleAuthorizationToken) authorization;

        boolean skip = SecurityPredicate.initSecurityApis(getSecurityApis()).skip(authorizationToken.getRequestPath());
        return null;
    }

    @Override
    public boolean supports(Class<? extends Authorization> authorizationClass) {
        return SimpleAuthorizationToken.class.isAssignableFrom(authorizationClass);
    }


    private Set<String> getSecurityApis() {
        Set<String> skipApis = configCenter.getGatewayConfig().getDefaultSkipApis();
        skipApis.addAll(configCenter.getGatewayConfig().getSkipApis());
        return skipApis;
    }
}
