package com.ziyao.ideal.gateway.filter.intercept;

import com.ziyao.ideal.gateway.common.response.SecurityPredicate;
import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.config.GatewayConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 访问资源黑名单拦截器
 *
 * @author ziyao zhang
 */
@Component
@RequiredArgsConstructor
public class ResourceBlacklistInterceptor implements GatewayInterceptor {

    private ConfigCenter configCenter;

    @Override
    public void intercept(InterceptContext context) {

        if (getIllegalApis().isIllegal(context.requestUri())) {
//            throw Exceptions.createIllegalAccessException(defaultAccessToken.getApi());
        }

    }

    private SecurityPredicate getIllegalApis() {
        GatewayConfig gatewayConfig = configCenter.getGatewayConfig();
        return SecurityPredicate.initIllegalApis(gatewayConfig.getDefaultDisallowApis())
                .addIllegalApis(gatewayConfig.getDisallowApis());
    }
}
