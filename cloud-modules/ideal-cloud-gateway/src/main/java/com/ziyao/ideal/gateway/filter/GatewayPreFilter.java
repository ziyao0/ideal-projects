package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.support.GatewayStopWatches;
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
public class GatewayPreFilter extends AbstractGlobalFilter {

    private final ConfigCenter configCenter;

    @Override
    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (configCenter.getLoggerConfig().isFilterWatch()) {
            GatewayStopWatches.enabled(exchange);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
