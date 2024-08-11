package com.ziyao.ideal.gateway.filter.intercept;

import com.ziyao.ideal.gateway.config.ConfigurationManager;
import com.ziyao.ideal.gateway.config.GatewayConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * IP黑名单拦截器
 *
 * @author ziyao zhang
 */
@Component
@RequiredArgsConstructor
public class IPBlacklistInterceptor implements GatewayInterceptor {

    private final ConfigurationManager configurationManager;

    @Override
    public void intercept(InterceptContext context) {

        Set<String> ips = configurationManager.getIpBlacklistConfig().getIps();

    }
}
