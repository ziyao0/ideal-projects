package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.gateway.authorization.AuthorizationManager;
import com.ziyao.ideal.gateway.authorization.AuthorizationToken;
import com.ziyao.ideal.gateway.authorization.convertor.AuthorizationConvertor;
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

    private final AuthorizationConvertor authorizationConvertor;
    private final AuthorizationManager authorizationManager;
    private final RequestInterceptor requestInterceptor;

    @Override
    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 从请求头提取认证 token
        return Mono.fromCallable(() -> authorizationConvertor.convert(exchange))
                // 过滤黑名单、跨域等相关信息
                .doOnNext(requestInterceptor::intercept)
                .flatMap(authorization -> Mono.fromCallable(() -> (AuthorizationToken) authorizationManager.authorize(authorization))
                        // 存储授权信息
                        .doOnNext(authorizationToken -> RequestAttributes.storeAuthorizationToken(exchange, authorizationToken))
                        // 处理授权结果
                        .flatMap(token -> SecurityUtils.authorized(token)
                                ? chain.filter(exchange)
                                : GatewayErrors.createUnauthorizedException(token.getResponseDetails().message()))
                );
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
