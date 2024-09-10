package com.ziyao.ideal.gateway.core.decorator;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;

/**
 * @author ziyao zhang
 */
public class RequestRecordDecorator extends ServerHttpRequestDecorator {

    public RequestRecordDecorator(ServerHttpRequest delegate) {
        super(delegate);
    }

    @Override
    public @NonNull HttpHeaders getHeaders() {
        return super.getHeaders();
    }

    @Override
    public @NonNull Flux<DataBuffer> getBody() {
        return super.getBody();
    }

    @Override
    public @NonNull HttpMethod getMethod() {
        return super.getMethod();
    }
}
