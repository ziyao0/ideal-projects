package com.ziyao.ideal.gateway.core.decorator;

import com.ziyao.ideal.gateway.core.RequestAttributes;
import com.ziyao.ideal.gateway.filter.body.ReqResRecord;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;

/**
 * @author ziyao zhang
 */
public class RequestRecordDecorator extends ServerHttpRequestDecorator {

    private final ServerWebExchange exchange;

    public RequestRecordDecorator(ServerWebExchange exchange) {
        super(exchange.getRequest());
        this.exchange = exchange;
    }

    @Override
    public @NonNull HttpHeaders getHeaders() {
        return super.getHeaders();
    }

    @Override
    public @NonNull Flux<DataBuffer> getBody() {
        return super.getBody().map(dataBuffer -> {
            byte[] content = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(content);
            DataBufferUtils.release(dataBuffer); // 释放资源
            String requestBody = new String(content, StandardCharsets.UTF_8);

            // 打印请求体
            System.out.println("Request Body: " + requestBody);
            RequestAttributes.storeAttribute(exchange, ReqResRecord.of(requestBody));
            // 返回读取的内容，以便继续处理
            return exchange.getResponse().bufferFactory().wrap(content);
        });
    }

    @Override
    public @NonNull HttpMethod getMethod() {
        return super.getMethod();
    }
}
