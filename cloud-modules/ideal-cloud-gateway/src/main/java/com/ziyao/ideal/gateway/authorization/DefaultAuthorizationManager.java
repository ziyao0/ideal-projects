package com.ziyao.ideal.gateway.authorization;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ziyao zhang
 */
@Service
public class DefaultAuthorizationManager implements AuthorizationManager {

    private final List<AuthorizationProvider> providers;

    public DefaultAuthorizationManager(List<AuthorizationProvider> providers) {
        this.providers = List.copyOf(providers);
    }

    @Override
    public Authorization authorize(Authorization authorization) {
        for (AuthorizationProvider authorizationProvider : this.providers) {

            if (authorizationProvider.supports(authorization.getClass())) {
                return authorizationProvider.authorize(authorization);
            }
        }
        // 未找到能处理的授权提供者
        return null;
    }
}
