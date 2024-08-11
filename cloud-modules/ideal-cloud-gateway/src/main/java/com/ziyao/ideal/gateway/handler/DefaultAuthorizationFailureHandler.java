package com.ziyao.ideal.gateway.handler;

import com.ziyao.ideal.gateway.authorization.AuthorizationFailureHandler;
import com.ziyao.ideal.gateway.authorization.support.DataBuffers;
import com.ziyao.ideal.gateway.authorization.support.ResponseMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.Serial;

/**
 * @author ziyao
 */
@Slf4j
@Component
public class DefaultAuthorizationFailureHandler implements AuthorizationFailureHandler {

    @Override
    public Mono<Void> onFailureResume(ServerWebExchange exchange, Throwable throwable) {
        log.error(throwable.getMessage(), throwable);
        ResponseMetadata responseMetadata = new ResponseMetadata() {
            @Serial
            private static final long serialVersionUID = -2545416176368395645L;

            @Override
            public Integer getStatus() {
                return 403;
            }

            @Override
            public String getMessage() {
                return "越权访问拦截";
            }
        };
        // TODO: 2023/9/14 异常处理
        return DataBuffers.writeWith(exchange.getResponse(), responseMetadata);

    }
}
