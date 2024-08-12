package com.ziyao.ideal.gateway.handler;

import com.ziyao.ideal.gateway.security.AuthorizationFailureHandler;
import com.ziyao.ideal.gateway.common.response.DataBuffers;
import com.ziyao.ideal.gateway.common.response.ResponseMetadata;
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

        ResponseMetadata metadata = ResponseMetadata.getInstance(403, "越权访问拦截");

        // TODO: 2023/9/14 异常处理
        return DataBuffers.writeWith(exchange.getResponse(), metadata);

    }
}
