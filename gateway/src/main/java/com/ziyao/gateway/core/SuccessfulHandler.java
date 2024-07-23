package com.ziyao.gateway.core;

import org.springframework.web.server.ServerWebExchange;

/**
 * @author ziyao zhang
 */
@FunctionalInterface
public interface SuccessfulHandler<T> {

    /**
     * 成功时调用方法
     */
    ServerWebExchange onSuccessful(ServerWebExchange exchange, T t);
}
