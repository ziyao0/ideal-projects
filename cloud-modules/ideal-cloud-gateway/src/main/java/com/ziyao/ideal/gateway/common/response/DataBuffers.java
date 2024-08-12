package com.ziyao.ideal.gateway.common.response;

import com.ziyao.ideal.core.Strings;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoOperator;

import java.util.Objects;

/**
 * @author ziyao zhang
 */
public abstract class DataBuffers {

    private DataBuffers() {
    }

    /**
     * 组装响应对象
     */
    public static Mono<Void> writeWith(ServerHttpResponse response, ResponseMetadata responseMetadata, HttpStatusCode statusCode) {
        return writeWith(response, ResponseMetadata.getInstance(statusCode.value(), responseMetadata.getMessage()));
    }

    /**
     * 组装响应对象
     */
    public static Mono<Void> writeWith(ServerWebExchange exchange, ResponseMetadata responseMetadata) {
        return writeWith(exchange.getResponse(), responseMetadata);
    }


    /**
     * 组装响应对象
     */
    public static Mono<Void> writeWith(ServerHttpResponse response, ResponseMetadata responseMetadata) {
        return writeWith(response, responseMetadata.getStatus(), responseMetadata.getMessage());
    }

    /**
     * 组装响应对象
     */
    public static Mono<Void> writeWith(ServerHttpResponse response, int status, String message) {
        // 设置响应状态码
        HttpStatus httpStatus = HttpStatus.resolve(status);
        if (Objects.nonNull(httpStatus)) {
            response.setStatusCode(HttpStatusCode.valueOf(status));
        }
        // 填充响应体
        DataBuffer dataBuffer = response.bufferFactory()
                .wrap(Strings.toBytes(ResponseMetadata.getInstance(status, message)));
        // 填充响应类型
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(MonoOperator.just(dataBuffer));
    }

}
