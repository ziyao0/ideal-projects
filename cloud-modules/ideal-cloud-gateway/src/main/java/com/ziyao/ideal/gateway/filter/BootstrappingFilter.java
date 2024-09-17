package com.ziyao.ideal.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.core.RequestAttributes;
import com.ziyao.ideal.gateway.core.decorator.RequestRecordDecorator;
import com.ziyao.ideal.gateway.core.decorator.ResponseRecordDecorator;
import com.ziyao.ideal.gateway.filter.body.ReqResRecord;
import com.ziyao.ideal.gateway.support.GatewayStopWatches;
import com.ziyao.ideal.gateway.support.ParameterNames;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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
            GatewayStopWatches.enable(exchange);
            GatewayStopWatches.start(ParameterNames.STOP_WATCH_NAMES, exchange);
            GatewayStopWatches.start(this.getTaskId(), exchange);
        }
        return doFilter(exchange, chain)
                .onErrorResume(throwable -> onError(exchange, throwable))
                .doFinally(signalType -> {
                    GatewayStopWatches.stop(ParameterNames.STOP_WATCH_NAMES, exchange);
                    Mono.fromRunnable(() -> {
                                // 异步任务逻辑
                                GatewayStopWatches.prettyPrint(exchange);
                                long elapsedTime = GatewayStopWatches.elapsedTime(exchange);
                                System.out.println(elapsedTime);
                                GatewayStopWatches.disable(exchange);
                                // 记录操作日志，发送告警信息等等
                                ReqResRecord reqResRecord = RequestAttributes.getAttribute(exchange, ReqResRecord.class);
                                System.out.println("请求响应参数：" + JSON.toJSONString(reqResRecord));
                            })
                            .subscribeOn(Schedulers.boundedElastic()) // 指定调度器
                            .subscribe(); // 订阅任务，确保其执行
                });
        // @formatter:on
    }

    @Override
    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 使用装饰后的请求继续处理
        Mono<Void> filter = chain.filter(exchange.mutate()
                .request(new RequestRecordDecorator(exchange))
                .response(new ResponseRecordDecorator(exchange))
                .build());
        GatewayStopWatches.stop(this.getTaskId(), exchange);
        return filter;
    }

    @Override
    public int getOrder() {
        return FilterOrder.Bootstrapping.getOrder();
    }
}
