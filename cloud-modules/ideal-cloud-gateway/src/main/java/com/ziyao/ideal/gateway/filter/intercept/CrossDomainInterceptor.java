package com.ziyao.ideal.gateway.filter.intercept;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.gateway.config.ConfigCenter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

/**
 * @author ziyao zhang
 */
@Component
@RequiredArgsConstructor
public class CrossDomainInterceptor implements GatewayInterceptor {

    private final ConfigCenter configCenter;

    @Override
    public void intercept(InterceptContext context) {

        Set<String> trustDomains = configCenter.getGatewayConfig().getTrustDomains();

        String domains = context.domains();
        if (!Collections.isEmpty(trustDomains)) {
            for (String trustDomain : trustDomains) {
                if (Objects.equals(domains, trustDomain)) {
                    return;
                }
            }
        }
        throw new RuntimeException("跨域异常");
    }
}
