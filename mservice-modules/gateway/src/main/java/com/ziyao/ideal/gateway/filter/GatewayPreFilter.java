package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.gateway.config.LoggerConfig;
import com.ziyao.ideal.gateway.core.GatewayStopWatches;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ziyao zhang
 */
@Component
public class GatewayPreFilter extends AbstractGlobalFilter {

    private final LoggerConfig loggerConfig;

    protected GatewayPreFilter(LoggerConfig loggerConfig) {
        this.loggerConfig = loggerConfig;
    }

    @Override
    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (loggerConfig.isFilterWatch()) {
            GatewayStopWatches.enabled(exchange);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
