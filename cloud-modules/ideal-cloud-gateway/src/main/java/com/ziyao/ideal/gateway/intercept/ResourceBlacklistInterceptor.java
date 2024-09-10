package com.ziyao.ideal.gateway.intercept;

import com.ziyao.ideal.gateway.core.SecurityPredicate;
import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.config.GatewayConfig;
import com.ziyao.ideal.gateway.authorization.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 访问资源黑名单拦截器
 *
 * @author ziyao zhang
 */
@Component
@RequiredArgsConstructor
public class ResourceBlacklistInterceptor implements RequestInterceptor {

    private final ConfigCenter configCenter;

    @Override
    public void intercept(Authorization authorization) {

        if (getIllegalApis().isIllegal(authorization.getRequestPath())) {
//            throw Exceptions.createIllegalAccessException(defaultAccessToken.getApi());
        }

    }

    private SecurityPredicate getIllegalApis() {
        GatewayConfig gatewayConfig = configCenter.getGatewayConfig();
        return SecurityPredicate.initIllegalApis(gatewayConfig.getDefaultDisallowApis())
                .addIllegalApis(gatewayConfig.getDisallowApis());
    }
}
