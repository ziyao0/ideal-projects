package com.ziyao.ideal.gateway.core.error;

import com.ziyao.ideal.gateway.core.Response;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

/**
 * @author ziyao zhang
 */
public abstract class GatewayErrors {

    private GatewayErrors() {
    }

    /**
     * 创建请求未认证异常
     *
     * @param message 异常信息
     * @return {@link GatewayException}
     */
    public static Mono<Void> createUnauthorizedException(String message) {
        return Mono.error(new GatewayException(401, message));
    }

    /**
     * 创建越权访问异常
     *
     * @param message 异常信息
     * @return {@link GatewayException}
     */
    public static Mono<Void> createForbiddenException(String message) {
        return Mono.error(new GatewayException(403, message));
    }

    /**
     * 创建非法访问
     *
     * @param message 异常信息
     * @return {@link GatewayException}
     */
    public static Mono<Void> createIllegalAccessException(String message) {
        return Mono.error(new GatewayException(403, message));
    }

    public static Mono<Void> createException(Integer status, String message) {
        return Mono.error(new GatewayException(status, message));
    }

    public static Mono<Void> createException(HttpStatus httpStatus) {
        return Mono.error(new GatewayException(httpStatus.value(), httpStatus.getReasonPhrase()));
    }

    public static Mono<Void> createException(Response response) {
        return Mono.error(new GatewayException(response));
    }

    public static Mono<Void> createException(String message, Throwable throwable) {
        return Mono.error(new GatewayException(500, "服务器内部错误：" + message, throwable));
    }
}
