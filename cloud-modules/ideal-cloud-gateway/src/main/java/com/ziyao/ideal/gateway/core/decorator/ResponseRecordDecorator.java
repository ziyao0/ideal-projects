package com.ziyao.ideal.gateway.core.decorator;

import com.ziyao.ideal.gateway.core.DataBuffers;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;

import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author ziyao zhang
 */
public class ResponseRecordDecorator extends ServerHttpResponseDecorator {

    public ResponseRecordDecorator(ServerHttpResponse delegate) {
        super(delegate);
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
            System.out.println("Response Body: " + responseBody);

            // 继续传递响应数据
            return bufferFactory().wrap(content);
        });

        // 写入拦截后的响应数据
        return super.writeWith(interceptedBody);
    }
}
