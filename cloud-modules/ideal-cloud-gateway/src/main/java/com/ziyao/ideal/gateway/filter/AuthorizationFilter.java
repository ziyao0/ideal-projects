package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.gateway.authorization.Authorization;
import com.ziyao.ideal.gateway.authorization.AuthorizationManager;
import com.ziyao.ideal.gateway.authorization.convertor.AuthorizationConvertor;
import com.ziyao.ideal.gateway.intercept.RequestInterceptor;
import com.ziyao.ideal.gateway.support.AuthorizationUtils;
import com.ziyao.ideal.gateway.support.GatewayStopWatches;
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
        // 从请求头提取认证token

//        Authorization authorization = authorizationConvertor.convert(exchange);
//        // 过滤黑名单、跨域等相关信息
//        requestInterceptor.intercept(authorization);
//
//        Authorization authorized = authorizationManager.authorize(authorization);
//
//        if (AuthorizationUtils.unauthorized(authorized)) {
//            // 未验证通过
//        }


        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
