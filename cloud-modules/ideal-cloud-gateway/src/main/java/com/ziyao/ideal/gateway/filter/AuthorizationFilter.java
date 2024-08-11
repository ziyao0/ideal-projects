package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.gateway.authorization.AccessTokenExtractor;
import com.ziyao.ideal.gateway.authorization.AccessTokenValidator;
import com.ziyao.ideal.gateway.authorization.AuthorizationManager;
import com.ziyao.ideal.gateway.authorization.GatewayStopWatches;
import com.ziyao.ideal.gateway.authorization.support.RequestAttributes;
import com.ziyao.ideal.gateway.authorization.support.SecurityPredicate;
import com.ziyao.ideal.gateway.authorization.token.DefaultAccessToken;
import com.ziyao.ideal.gateway.config.GatewayConfig;
import com.ziyao.ideal.gateway.error.GatewayErrors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * grant
 *
 * @author ziyao zhang
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends AbstractGlobalFilter {


    private final AuthorizationManager authorizationManager;
    private final GatewayConfig gatewayConfig;

    @Override
    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 从请求头提取认证token
        DefaultAccessToken defaultAccessToken = AccessTokenExtractor.extractForHeaders(exchange);
        return Mono.just(defaultAccessToken).flatMap(access -> {
            boolean skip = SecurityPredicate.initSecurityApis(getSecurityApis()).skip(access.getApi());
            Mono<Void> filter;
            if (skip) {
                filter = chain.filter(exchange);
            } else {
                // 快速校验认证token
                AccessTokenValidator.validateToken(access);
                filter = authorizationManager.getAuthorization(access.getName()).authorize(access)
                        .flatMap(author -> {
                            if (author.isAuthorized()) {
                                // TODO: 2023/10/8 成功后向exchange存储认证成功信息
//                                RequestAttributes.storeAuthorizerContext(exchange, null);
                                return chain.filter(exchange);
                            } else {
                                return GatewayErrors.createUnauthorizedException(author.getMessage());
                            }
                        });
            }
            GatewayStopWatches.stop(getBeanName(), exchange);
            return filter;
        });
    }

    private Set<String> getSecurityApis() {
        Set<String> skipApis = gatewayConfig.getDefaultSkipApis();
        skipApis.addAll(gatewayConfig.getSkipApis());
        return skipApis;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
