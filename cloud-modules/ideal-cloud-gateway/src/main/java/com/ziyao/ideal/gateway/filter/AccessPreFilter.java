package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.gateway.core.AccessTokenExtractor;
import com.ziyao.ideal.gateway.core.GatewayStopWatches;
import com.ziyao.ideal.gateway.core.token.DefaultAccessToken;
import com.ziyao.ideal.gateway.core.factory.AccessChainFactory;
import jakarta.annotation.Resource;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 前置访问控制过滤器
 *
 * @author zhangziyao
 */
@Component
public class AccessPreFilter extends AbstractGlobalFilter {

    @Resource
    private AccessChainFactory accessChainFactory;

    @Override
    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 2023/9/9 从请求头提取请求路径，请求ip等相关信息，进行前置校验   快速失败
        DefaultAccessToken defaultAccessToken = AccessTokenExtractor.extractForHeaders(exchange);
        return Mono.just(defaultAccessToken)
                .flatMap(access -> {
                    accessChainFactory.filter(access);
                    GatewayStopWatches.stop(super.getBeanName(), exchange);
                    return chain.filter(exchange);
                });
    }


    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 1;
    }
}
