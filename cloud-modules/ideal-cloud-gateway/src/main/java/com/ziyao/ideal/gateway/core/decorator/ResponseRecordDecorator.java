package com.ziyao.ideal.gateway.core.decorator;

import com.alibaba.fastjson.JSON;
import com.ziyao.ideal.gateway.core.RequestAttributes;
import com.ziyao.ideal.gateway.filter.body.ReqResRecord;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author ziyao zhang
 */
public class ResponseRecordDecorator extends ServerHttpResponseDecorator {

    private final ServerWebExchange exchange;

    public ResponseRecordDecorator(ServerWebExchange exchange) {
        super(exchange.getResponse());
        this.exchange = exchange;
    }

    @Override
    public @NonNull Mono<Void> writeWith(@NonNull Publisher<? extends DataBuffer> body) {
        Flux<? extends DataBuffer> fluxBody = Flux.from(body);

        // 拦截响应数据
        Flux<DataBuffer> interceptedBody = fluxBody.map(dataBuffer -> {
            // 读取响应内容
            byte[] content = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(content);
            DataBufferUtils.release(dataBuffer); // 释放资源
            String responseBody = new String(content, StandardCharsets.UTF_8);

            // 打印响应数据
            System.out.println("Response Body: " + JSON.parse(responseBody));
            ReqResRecord reqResRecord = RequestAttributes.getAttribute(exchange, ReqResRecord.class);
            reqResRecord.setResContent(responseBody);
            RequestAttributes.storeAttribute(exchange, reqResRecord);
            // 继续传递响应数据
            return bufferFactory().wrap(content);
        });

        // 写入拦截后的响应数据
        return super.writeWith(interceptedBody);
    }
}
