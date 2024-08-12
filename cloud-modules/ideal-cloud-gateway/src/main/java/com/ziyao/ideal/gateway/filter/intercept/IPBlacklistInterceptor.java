package com.ziyao.ideal.gateway.filter.intercept;

import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.support.IPMatchUtils;
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

    private final ConfigCenter configCenter;

    @Override
    public void intercept(InterceptContext context) {

        Set<String> ips = configCenter.getIpBlacklistConfig().getIps();

        if (IPMatchUtils.matchExactIp(ips, context.clientIp())) {
            // ip黑名单
        }

        Set<String> fuzzyIps = configCenter.getIpBlacklistConfig().getFuzzyIps();
        if (IPMatchUtils.matchFuzzyIp(fuzzyIps, context.clientIp())) {

        }

        Set<String> ipLimits = configCenter.getIpBlacklistConfig().getIpLimits();
        if (IPMatchUtils.matchIpRange(ipLimits, context.clientIp())) {

        }
    }
}
