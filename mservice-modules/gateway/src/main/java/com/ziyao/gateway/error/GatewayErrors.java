package com.ziyao.gateway.error;

import reactor.core.publisher.Mono;

/**
 * @author ziyao zhang
 */
public abstract class GatewayErrors {

    private GatewayErrors() {
    }

    public static Mono<Void> createUnauthorizedException(String message) {
//        return Mono.error(Exceptions.createUnauthorizedException(message));
        return null;
    }

//    /**
//     * 创建越权访问异常
//     *
//     * @param message 异常信息
//     * @return {@link HarborException}
//     */
//    public static Mono<Void> createForbiddenException(String message) {
//        return Mono.error(Exceptions.createForbiddenException(message));
//    }
//
//    /**
//     * 创建非法访问
//     *
//     * @param message 异常信息
//     * @return {@link HarborException}
//     */
//    public static Mono<Void> createIllegalAccessException(String message) {
//        return Mono.error(Exceptions.createIllegalAccessException(message));
//    }
//
//    /**
//     * 创建非法访问
//     *
//     * @param message 异常信息
//     * @return {@link HarborException}
//     */
//    public static Mono<Void> createIllegalArgumentException(String message) {
//        return Mono.error(Exceptions.createIllegalArgumentException(message));
//    }
}
