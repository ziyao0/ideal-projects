package com.ziyao.ideal.gateway.handler;

import com.ziyao.ideal.gateway.core.DataBuffers;
import com.ziyao.ideal.gateway.core.ResponseDetails;
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
public class DefaultAuthorizationFailureHandler implements AuthorizationFailureHandler {

    @Override
    public Mono<Void> onFailureResume(ServerWebExchange exchange, Throwable throwable) {
        log.error(throwable.getMessage(), throwable);

        ResponseDetails metadata = ResponseDetails.of(403, "越权访问拦截");

        if (throwable instanceof GatewayException e) {
            return DataBuffers.writeWith(exchange.getResponse(), e.status(), e.message());
        }

        // TODO: 2023/9/14 异常处理
        return DataBuffers.writeWith(exchange.getResponse(), metadata);

    }
}
