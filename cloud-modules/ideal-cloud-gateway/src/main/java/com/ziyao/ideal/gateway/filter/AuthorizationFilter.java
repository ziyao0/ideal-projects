package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.gateway.support.GSecurityContextExtractor;
import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.support.GatewayStopWatches;
import com.ziyao.ideal.gateway.common.response.RequestAttributes;
import com.ziyao.ideal.gateway.common.response.SecurityPredicate;
import com.ziyao.ideal.gateway.security.DefaultGSecurityContext;
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

    private final ConfigCenter configCenter;

    @Override
    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 从请求头提取认证token
        DefaultGSecurityContext defaultGatewaySecurityContext = GSecurityContextExtractor.extractForHeaders(exchange);

        DefaultGSecurityContext securityContext = RequestAttributes.getAttribute(exchange, DefaultGSecurityContext.class);

        return Mono.just(defaultGatewaySecurityContext).flatMap(access -> {
            boolean skip = SecurityPredicate.initSecurityApis(getSecurityApis()).skip(access.getRequestUri());
            Mono<Void> filter;
            if (skip) {
                filter = chain.filter(exchange);
            } else {
                // 快速校验认证token
//                AccessTokenValidator.validateToken(access);
//                filter = authorizationManager.getAuthorization(access.getName()).authorize(access)
//                        .flatMap(author -> {
//                            if (author.isAuthorized()) {
//                                // TODO: 2023/10/8 成功后向exchange存储认证成功信息
////                                RequestAttributes.storeAuthorizerContext(exchange, null);
//                                return chain.filter(exchange);
//                            } else {
//                                return GatewayErrors.createUnauthorizedException(author.getMessage());
//                            }
//                        });
                filter = chain.filter(exchange);
            }
            GatewayStopWatches.stop(getBeanName(), exchange);
            return filter;
        });
    }

    private Set<String> getSecurityApis() {
        Set<String> skipApis = configCenter.getGatewayConfig().getDefaultSkipApis();
        skipApis.addAll(configCenter.getGatewayConfig().getSkipApis());
        return skipApis;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
