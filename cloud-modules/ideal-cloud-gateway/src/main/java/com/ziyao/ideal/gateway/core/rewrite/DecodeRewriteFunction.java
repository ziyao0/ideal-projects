package com.ziyao.ideal.gateway.core.rewrite;

import org.reactivestreams.Publisher;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author ziyao zhang
 */
public class DecodeRewriteFunction extends AbstractRewriteFunction {
    @Override
    public Publisher<byte[]> apply(ServerWebExchange serverWebExchange, byte[] bytes) {
        return null;
    }
}
