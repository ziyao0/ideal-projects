package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.gateway.authorization.AuthorizationToken;
import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.core.RequestAttributes;
import com.ziyao.ideal.gateway.core.decorator.RequestRecordDecorator;
import com.ziyao.ideal.gateway.core.decorator.ResponseRecordDecorator;
import com.ziyao.ideal.gateway.filter.body.ReqRes;
import com.ziyao.ideal.gateway.service.MonitoringService;
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
    private final MonitoringService monitoringService;

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
                                long time = GatewayStopWatches.elapsedTime(exchange);
                                GatewayStopWatches.prettyPrint(exchange);
                                GatewayStopWatches.disable(exchange);
                                // 处理后续步骤
                                ReqRes reqRes = RequestAttributes.getAttributeOrDefault(exchange, ReqRes.class, new ReqRes());
                                reqRes.setTime(time);
                                doFinally(exchange, reqRes);



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

    /**
     * 过滤器后续处理逻辑
     *
     * @param exchange the current server exchange
     * @param reqRes   请求响应数据
     */
    private void doFinally(ServerWebExchange exchange, ReqRes reqRes) {

        // 记录操作日志，发送告警信息等等
        AuthorizationToken authorizationToken = RequestAttributes.loadAuthorizationToken(exchange);

        // 记录用户行为
        monitoringService.recordUserBehavior(authorizationToken, reqRes);
    }

    @Override
    public int getOrder() {
        return FilterOrder.Bootstrapping.getOrder();
    }
}
