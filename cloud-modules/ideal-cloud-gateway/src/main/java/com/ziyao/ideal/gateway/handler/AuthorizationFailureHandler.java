package com.ziyao.ideal.gateway.handler;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ziyao zhang
 */
@FunctionalInterface
public interface AuthorizationFailureHandler {

    /**
     * 失败时调用
     * <p>
     * {@link Throwable}异常信息
     * T 返回类型
     */
    Mono<Void> onFailureResume(ServerWebExchange exchange, Throwable throwable);

}
