package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.gateway.authorization.AuthorizationToken;
import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.config.SystemConfig;
import com.ziyao.ideal.gateway.core.RequestAttributes;
import com.ziyao.ideal.gateway.core.error.GatewayErrors;
import com.ziyao.ideal.gateway.service.PermissionService;
import com.ziyao.ideal.gateway.support.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ziyao zhang
 */
@Component
@RequiredArgsConstructor
public class PermissionFilter extends AbstractAfterAuthenticationFilter {

    private final ConfigCenter configCenter;
    private final PermissionService permissionService;

    @Override
    protected Mono<Void> onSuccess(ServerWebExchange exchange, GatewayFilterChain chain) {
        SystemConfig systemConfig = configCenter.getSystemConfig();
        if (!systemConfig.isEnablePermissionVerify()) {
            return chain.filter(exchange);
        }
        AuthorizationToken authorizationToken = RequestAttributes.loadAuthorizationToken(exchange);
        return permissionService.verify(authorizationToken)
                .flatMap(verificationToken ->
                        SecurityUtils.authorized(verificationToken)
                                ? chain.filter(exchange)
                                : GatewayErrors.createException(verificationToken.getResponse())
                );
    }

    @Override
    public int getOrder() {
        return FilterOrder.Permission.getOrder();
    }
}
