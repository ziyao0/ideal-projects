package com.ziyao.ideal.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 鉴权成功后执行的过滤器
 *
 * @author ziyao zhang
 */
public abstract class AbstractAfterAuthenticationFilter extends AbstractGlobalFilter {

    /**
     * 处理 Web 请求并（可选）通过给定的 {@code GatewayFilterChain} 委托给下一个 {@link GatewayFilter}。
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} 指示请求处理何时完成
     */
    @Override
    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (isAuthenticated(exchange)) {
            return doOnSuccess(exchange, chain);
        }
        return chain.filter(exchange);
    }

    /**
     * 授权成功后执行
     *
     * @param exchange the current server exchange
     * @param chain    允许 {@link GatewayFilter} 委托给链中的下一个的协定
     * @return {@code Mono<Void>} 指示请求处理何时完成
     */
    protected abstract Mono<Void> doOnSuccess(ServerWebExchange exchange, GatewayFilterChain chain);
}
