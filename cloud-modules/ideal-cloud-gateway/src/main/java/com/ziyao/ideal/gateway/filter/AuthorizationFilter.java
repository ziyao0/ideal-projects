package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.service.UserCacheService;
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

    private final UserCacheService userCacheService;
    private final ConfigCenter configCenter;

    @Override
    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 从请求头提取认证token


//        return Mono.just(defaultGatewaySecurityContext).flatMap(access -> {
//            boolean skip = SecurityPredicate.initSecurityApis(getSecurityApis()).skip(access.getRequestUri());
//            Mono<Void> filter;
//            if (skip) {
//                filter = chain.filter(exchange);
//            } else {
//                Optional<SessionUser> sessionUser = sessionContextService.load(securityContext.getToken());
//                if (sessionUser.isPresent()) {
//                    return chain.filter(exchange);
//                }
//                // 快速校验认证token
////                AccessTokenValidator.validateToken(access);
////                filter = authorizationManager.getAuthorization(access.getName()).authorize(access)
////                        .flatMap(author -> {
////                            if (author.isAuthorized()) {
////                                // TODO: 2023/10/8 成功后向exchange存储认证成功信息
//////                                RequestAttributes.storeAuthorizerContext(exchange, null);
////                                return chain.filter(exchange);
////                            } else {
////                                return GatewayErrors.createUnauthorizedException(author.getMessage());
////                            }
////                        });
//                filter = chain.filter(exchange);
//            }
//            GatewayStopWatches.stop(getBeanName(), exchange);
//            return filter;
//        });
        return null;
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
