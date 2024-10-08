package com.ziyao.ideal.gateway.core.decorator;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.gateway.core.RequestAttributes;
import com.ziyao.ideal.gateway.filter.body.ReqRes;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

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

        return super.getBody().collectList()
                .flatMapMany(dataBuffers -> {
                    if (Collections.isEmpty(dataBuffers)) {
                        return super.getBody();
                    }
                    DataBuffer dataBuffer = exchange.getResponse().bufferFactory().join(dataBuffers);
                    byte[] content = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(content);
                    // 释放内存
                    DataBufferUtils.release(dataBuffer);
                    // 存储请求体内容
                    RequestAttributes.storeAttribute(exchange, ReqRes.of(Strings.toString(content)));
                    // 返回读取的内容，以便继续处理
                    return Flux.just(exchange.getResponse().bufferFactory().wrap(content));
                });
    }

    @Override
    public @NonNull HttpMethod getMethod() {
        return super.getMethod();
    }
}
