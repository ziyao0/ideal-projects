package com.ziyao.ideal.gateway.core.error;

import com.ziyao.ideal.gateway.core.Response;

import java.io.Serial;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class GatewayException extends RuntimeException implements Response {
    @Serial
    private static final long serialVersionUID = -3435528093859682944L;


    private final Response response;

    public GatewayException() {
        // TODO: 2023/9/24 业务异常
        this.response = null;
    }

    public GatewayException(Response Response) {
        this.response = Response;
    }

    public GatewayException(Integer status, String message) {
        this.response = Response.of(status, message);
    }

    public GatewayException(Integer status, String message, Throwable cause) {
        super(message, cause);
        this.response = Response.of(status, message);
    }

    @Override
    public Integer status() {
        return response.status();
    }


    @Override
    public String message() {
        return response.message();
    }
}
