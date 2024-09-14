package com.ziyao.ideal.gateway.core.decorator;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;

/**
 * @author ziyao zhang
 */
public class RequestRecordDecorator extends ServerHttpRequestDecorator {

    private final DataBufferFactory bufferFactory;

    public RequestRecordDecorator(ServerHttpRequest delegate, DataBufferFactory bufferFactory) {
        super(delegate);
        this.bufferFactory = bufferFactory;
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

            // 返回读取的内容，以便继续处理
            return bufferFactory.wrap(content);
        });
    }

    @Override
    public @NonNull HttpMethod getMethod() {
        return super.getMethod();
    }
}
