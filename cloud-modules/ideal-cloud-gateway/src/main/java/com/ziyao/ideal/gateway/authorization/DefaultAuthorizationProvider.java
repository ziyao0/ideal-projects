package com.ziyao.ideal.gateway.authorization;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.core.Response;
import com.ziyao.ideal.gateway.core.SecurityPredicate;
import com.ziyao.ideal.gateway.service.PrincipalCacheService;
import com.ziyao.ideal.security.core.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Component
@RequiredArgsConstructor
public class DefaultAuthorizationProvider implements AuthorizationProvider {

    private final ConfigCenter configCenter;
    private final PrincipalCacheService principalCacheService;

    @Override
    public Authorization authorize(Authorization authorization) {
        DefaultAuthorizationToken authorizationToken = (DefaultAuthorizationToken) authorization;
        AuthorizationToken.Builder builder = AuthorizationToken.form(authorizationToken);

        if (isSecurity(authorizationToken)) {
            builder.authorized();
            return builder.build();
        }
        Optional<SessionUser> sessionUser = principalCacheService.load(authorizationToken.getToken());
        if (sessionUser.isEmpty()) {
            builder.response(Response.of(401, "请求未认证"));
            return builder.build();
        }
        return builder.sessionUser(sessionUser.get()).authorized().build();
    }

    private boolean isSecurity(DefaultAuthorizationToken authorizationToken) {
        String requestPath = authorizationToken.getRequestPath();
        if (Strings.isEmpty(requestPath)) {
            return false;
        }
        return SecurityPredicate.initSecurityApis(getSecurityApis()).skip(authorizationToken.getRequestPath());
    }

    private Set<String> getSecurityApis() {
        Set<String> skipApis = configCenter.getGatewayConfig().getDefaultSkipApis();
        skipApis.addAll(configCenter.getGatewayConfig().getSkipApis());
        return skipApis;
    }

    @Override
    public boolean supports(Class<? extends Authorization> authorizationClass) {
        return DefaultAuthorizationToken.class.isAssignableFrom(authorizationClass);
    }
}
