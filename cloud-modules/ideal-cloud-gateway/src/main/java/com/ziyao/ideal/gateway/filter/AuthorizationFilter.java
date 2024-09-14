package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.gateway.authorization.Authorization;
import com.ziyao.ideal.gateway.authorization.AuthorizationManager;
import com.ziyao.ideal.gateway.authorization.AuthorizationToken;
import com.ziyao.ideal.gateway.authorization.convertor.AuthorizationConvertor;
import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.config.SystemConfig;
import com.ziyao.ideal.gateway.core.RequestAttributes;
import com.ziyao.ideal.gateway.core.error.GatewayErrors;
import com.ziyao.ideal.gateway.intercept.RequestInterceptor;
import com.ziyao.ideal.gateway.support.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * grant
 *
 * @author ziyao zhang
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends AbstractGlobalFilter {

    private final ConfigCenter configCenter;
    private final AuthorizationConvertor authorizationConvertor;
    private final AuthorizationManager authorizationManager;
    private final RequestInterceptor requestInterceptor;

    @Override
    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        SystemConfig systemConfig = configCenter.getSystemConfig();
        // 从请求头提取认证 token
        Authorization authorization = authorizationConvertor.convert(exchange);
        // 过滤黑名单、跨域等相关信息
        requestInterceptor.intercept(authorization);
        AuthorizationToken authorizationToken = (AuthorizationToken) authorizationManager.authorize(authorization);
        RequestAttributes.storeAuthorizationToken(exchange, authorizationToken);
        return SecurityUtils.authorized(authorizationToken)
                ? chain.filter(exchange)
                : systemConfig.isEnablePermissionVerify()
                ? GatewayErrors.createException(authorizationToken.getResponse())
                : chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
