package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.core.decorator.RequestRecordDecorator;
import com.ziyao.ideal.gateway.support.GatewayStopWatches;
import com.ziyao.ideal.gateway.support.ParameterNames;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * 引导执行
 * <p>
 * 第一个执行的过滤器
 *
 * @author ziyao zhang
 */
@Component
@RequiredArgsConstructor
public class BootstrappingFilter extends AbstractGlobalFilter {

    private final ConfigCenter configCenter;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // @formatter:off
        if (configCenter.getLoggerConfig().isFilterWatch()) {
            GatewayStopWatches.enabled(exchange);
            GatewayStopWatches.start(ParameterNames.STOP_WATCH_NAMES, exchange);
            GatewayStopWatches.start(this.getTaskId(), exchange);
        }

        Mono<Void> result = doFilter(exchange, chain)
                .onErrorResume(throwable -> onError(exchange, throwable));
        GatewayStopWatches.stop(this.getTaskId(), exchange);
        // @formatter:on
        return result;
    }

    @Override
    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 从响应中获取 DataBufferFactory
        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
        // 使用装饰后的请求继续处理
        return chain.filter(exchange.mutate().request(new RequestRecordDecorator(request, bufferFactory)).build());
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
