package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.gateway.core.decorator.ResponseRecordDecorator;
import com.ziyao.ideal.gateway.support.GatewayStopWatches;
import com.ziyao.ideal.gateway.support.ParameterNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 秒表信息打印
 *
 * @author ziyao zhang
 */
@Slf4j
@Component
public class FinalizationFilter extends AbstractGlobalFilter {

    @Override
    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // @formatter:off
        return chain.filter(exchange.mutate()
                        .response(
                                new ResponseRecordDecorator(exchange.getResponse()))
                        .build())
                .then(Mono.fromRunnable(() -> {
                    GatewayStopWatches.stop(ParameterNames.STOP_WATCH_NAMES, exchange);
                    GatewayStopWatches.prettyPrint(exchange);
                }));
        // @formatter:on
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
