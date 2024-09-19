package com.ziyao.ideal.gateway.core;

import com.alibaba.fastjson2.JSON;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

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
    public static Mono<Void> writeWith(ServerHttpResponse response, Response responseDetails, HttpStatusCode statusCode) {
        return writeWith(response, Response.of(statusCode.value(), responseDetails.message()));
    }

    /**
     * 组装响应对象
     */
    public static Mono<Void> writeWith(ServerWebExchange exchange, Response response) {
        return writeWith(exchange.getResponse(), response);
    }


    /**
     * 组装响应对象
     */
    public static Mono<Void> writeWith(ServerHttpResponse response, Response responseDetails) {
        return writeWith(response, responseDetails.status(), responseDetails.message());
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
                .wrap(JSON.toJSONBytes(Response.of(status, message)));
        // 填充响应类型
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(Mono.just(dataBuffer));
    }

}
