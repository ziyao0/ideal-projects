package com.ziyao.ideal.gateway.security.rewrite;

import org.reactivestreams.Publisher;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author ziyao zhang
 */
public class DecodeRewriteFunction extends com.ziyao.ideal.gateway.security.rewrite.AbstractRewriteFunction {
    @Override
    public Publisher<byte[]> apply(ServerWebExchange serverWebExchange, byte[] bytes) {
        return null;
    }
}