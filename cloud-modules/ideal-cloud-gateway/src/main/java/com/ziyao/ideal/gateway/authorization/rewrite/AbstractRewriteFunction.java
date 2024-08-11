package com.ziyao.ideal.gateway.authorization.rewrite;

import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;

/**
 * @author ziyao zhang
 */
public abstract class AbstractRewriteFunction implements RewriteFunction<byte[], byte[]> {


    /**
     * 判断是否为外部请求
     */
    public boolean isExternal() {
        return false;
    }

}
