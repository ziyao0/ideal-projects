package com.ziyao.ideal.gateway.authorization.rewrite;

import com.ziyao.ideal.gateway.filter.body.BodyExtractor;
import org.reactivestreams.Publisher;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author ziyao zhang
 */
public class EncodeRewriteFunction extends com.ziyao.ideal.gateway.authorization.rewrite.AbstractRewriteFunction {


    @Override
    public Publisher<byte[]> apply(ServerWebExchange serverWebExchange, byte[] bytes) {
        return BodyExtractor.extractRequestBody(serverWebExchange.getRequest());
    }
}
