package com.ziyao.ideal.gateway.handler;

import com.ziyao.ideal.gateway.core.DataBuffers;
import com.ziyao.ideal.gateway.core.Response;
import com.ziyao.ideal.gateway.core.error.GatewayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ziyao
 */
@Slf4j
@Component
public class DefaultOnErrorProcessor implements OnErrorProcessor {

    @Override
    public Mono<Void> onErrorResume(ServerWebExchange exchange, Throwable throwable) {
        log.error(throwable.getMessage(), throwable);

        Response metadata = Response.of(500, throwable.getMessage());

        if (throwable instanceof GatewayException e) {
            return DataBuffers.writeWith(exchange.getResponse(), e.status(), e.message());
        }

        // TODO: 2023/9/14 异常处理
        return DataBuffers.writeWith(exchange.getResponse(), metadata);

    }
}
