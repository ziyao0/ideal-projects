package com.ziyao.ideal.gateway.intercept;

import com.ziyao.ideal.gateway.authorization.Authorization;
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
public class IPBlacklistInterceptor implements RequestInterceptor {

    private final ConfigCenter configCenter;

    @Override
    public void intercept(Authorization authorization) {

        if (!configCenter.getSystemConfig().isEnableIpBlacklist()) {
            return;
        }

        Set<String> ips = configCenter.getIpBlacklistConfig().getIps();

        if (IPMatchUtils.matchExactIp(ips, authorization.getIp())) {
            // ip黑名单
        }

        Set<String> fuzzyIps = configCenter.getIpBlacklistConfig().getFuzzyIps();
        if (IPMatchUtils.matchFuzzyIp(fuzzyIps, authorization.getIp())) {

        }

        Set<String> ipLimits = configCenter.getIpBlacklistConfig().getIpLimits();
        if (IPMatchUtils.matchIpRange(ipLimits, authorization.getIp())) {

        }
    }
}
