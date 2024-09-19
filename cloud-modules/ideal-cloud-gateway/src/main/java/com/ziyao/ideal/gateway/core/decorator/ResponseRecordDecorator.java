package com.ziyao.ideal.gateway.core.decorator;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.gateway.core.RequestAttributes;
import com.ziyao.ideal.gateway.filter.body.ReqRes;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author ziyao zhang
 */
public class ResponseRecordDecorator extends ServerHttpResponseDecorator {

    private final ServerWebExchange exchange;
    private final DataBufferFactory dataBufferFactory;

    public ResponseRecordDecorator(ServerWebExchange exchange) {
        super(exchange.getResponse());
        this.exchange = exchange;
        this.dataBufferFactory = new DefaultDataBufferFactory();
    }

    @Override
    public @NonNull Mono<Void> writeWith(@NonNull Publisher<? extends DataBuffer> body) {
        Flux<? extends DataBuffer> fluxBody = Flux.from(body);
        return fluxBody.collectList().flatMap(dataBuffers -> {
            // 使用 join 方法将所有 DataBuffer 拼接成一个完整的 DataBuffer
            DataBuffer joinedDataBuffer = dataBufferFactory.join(dataBuffers);
            byte[] content = new byte[joinedDataBuffer.readableByteCount()];
            joinedDataBuffer.read(content);

            // 释放拼接后的 DataBuffer
            DataBufferUtils.release(joinedDataBuffer);

            // 记录并存储响应内容
            ReqRes reqRes = RequestAttributes.getAttributeOrDefault(exchange, ReqRes.class, new ReqRes());
            reqRes.setResBody(Strings.toString(content));
            RequestAttributes.storeAttribute(exchange, reqRes);
            // 重新包装拼接后的数据并写入响应
            return super.writeWith(Mono.just(bufferFactory().wrap(content)));
        });
    }
}
