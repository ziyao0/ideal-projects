package com.ziyao.ideal.gateway.authorization.factory.chain;

import com.ziyao.ideal.gateway.authorization.token.DefaultAccessToken;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author ziyao
 */
@Component
public class IPBlacklistHandler extends com.ziyao.ideal.gateway.authorization.factory.chain.AbstractSecurityHandler {
    @Override
    public void handle(DefaultAccessToken defaultAccessToken) {
        String ip = defaultAccessToken.getIp();
//        if ("127.0.0.1".equals(ip)) {
//            throw Exceptions.createIllegalAccessException(ip);
//        }
        this.checkedNextHandler(defaultAccessToken);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
