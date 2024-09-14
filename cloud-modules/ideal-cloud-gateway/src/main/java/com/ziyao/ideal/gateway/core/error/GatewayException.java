package com.ziyao.ideal.gateway.core.error;

import com.ziyao.ideal.gateway.core.ResponseDetails;

import java.io.Serial;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class GatewayException extends RuntimeException implements ResponseDetails {
    @Serial
    private static final long serialVersionUID = -3435528093859682944L;


    private final ResponseDetails responseDetails;

    public GatewayException() {
        // TODO: 2023/9/24 业务异常
        this.responseDetails = null;
    }

    public GatewayException(ResponseDetails ResponseDetails) {
        this.responseDetails = ResponseDetails;
    }

    public GatewayException(Integer status, String message) {
        this.responseDetails = ResponseDetails.of(status, message);
    }

    public GatewayException(Integer status, String message, Throwable cause) {
        super(message, cause);
        this.responseDetails = ResponseDetails.of(status, message);
    }

    @Override
    public Integer status() {
        return responseDetails.status();
    }


    @Override
    public String message() {
        return responseDetails.message();
    }
}
