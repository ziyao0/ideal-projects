package com.ziyao.ideal.gateway.filter.body;

import com.ziyao.ideal.gateway.filter.AbstractAfterAuthenticationFilter;
import com.ziyao.ideal.gateway.core.rewrite.DecodeRewriteFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ziyao zhang
 */
//@Component
@RequiredArgsConstructor
public class RequestBodyDecodeFilter extends AbstractAfterAuthenticationFilter {


    private final ModifyRequestBodyGatewayFilterFactory modifyRequestBodyGatewayFilterFactory;


    @Override
    protected Mono<Void> onSuccess(ServerWebExchange exchange, GatewayFilterChain chain) {
        DecodeRewriteFunction rewriteFunction = new DecodeRewriteFunction();
        ModifyRequestBodyGatewayFilterFactory.Config config = new ModifyRequestBodyGatewayFilterFactory.Config()
                .setRewriteFunction(rewriteFunction);
        return modifyRequestBodyGatewayFilterFactory.apply(config).filter(exchange, chain);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
