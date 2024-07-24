package com.ziyao.ideal.gateway.factory.chain;

import com.ziyao.ideal.gateway.config.GatewayConfig;
import com.ziyao.ideal.gateway.core.support.SecurityPredicate;
import com.ziyao.ideal.gateway.core.token.DefaultAccessToken;
import org.springframework.stereotype.Component;

/**
 * 请求路径黑名单
 *
 * @author ziyao
 */
@Component
public class ApiBlacklistHandler extends AbstractSecurityHandler {
    private final GatewayConfig gatewayConfig;

    public ApiBlacklistHandler(GatewayConfig gatewayConfig) {
        this.gatewayConfig = gatewayConfig;
    }

    @Override
    public void handle(DefaultAccessToken defaultAccessToken) {
        if (getIllegalApis().isIllegal(defaultAccessToken.getApi())) {
//            throw Exceptions.createIllegalAccessException(defaultAccessToken.getApi());
        }
        this.checkedNextHandler(defaultAccessToken);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private SecurityPredicate getIllegalApis() {
        return SecurityPredicate.initIllegalApis(gatewayConfig.getDefaultDisallowApis())
                .addIllegalApis(gatewayConfig.getDisallowApis());
    }
}
