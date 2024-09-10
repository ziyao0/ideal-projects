package com.ziyao.ideal.gateway.intercept;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.authorization.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

/**
 * @author ziyao zhang
 */
@Component
@RequiredArgsConstructor
public class CrossDomainInterceptor implements RequestInterceptor {

    private final ConfigCenter configCenter;

    @Override
    public void intercept(Authorization authorization) {

        Set<String> trustDomains = configCenter.getGatewayConfig().getTrustDomains();

        String domain = authorization.getDomain();
        if (!Collections.isEmpty(trustDomains)) {
            for (String trustDomain : trustDomains) {
                if (Objects.equals(domain, trustDomain)) {
                    return;
                }
            }
        }
        throw new RuntimeException("跨域异常");
    }
}
