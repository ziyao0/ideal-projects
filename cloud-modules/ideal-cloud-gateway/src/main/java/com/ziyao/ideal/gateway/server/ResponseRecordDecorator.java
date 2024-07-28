package com.ziyao.ideal.gateway.server;

import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

/**
 * @author ziyao zhang
 */
public class ResponseRecordDecorator extends ServerHttpResponseDecorator {
    public ResponseRecordDecorator(ServerHttpResponse delegate) {
        super(delegate);
    }

    @Override
    public @NonNull Mono<Void> writeWith(@NonNull Publisher<? extends DataBuffer> body) {
        return super.writeWith(body);
    }
}
