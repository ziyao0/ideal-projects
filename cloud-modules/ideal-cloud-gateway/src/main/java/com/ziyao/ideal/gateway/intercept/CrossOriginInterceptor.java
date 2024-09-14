package com.ziyao.ideal.gateway.intercept;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.gateway.authorization.Authorization;
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
public class CrossOriginInterceptor implements RequestInterceptor {

    private final ConfigCenter configCenter;

    @Override
    public void intercept(Authorization authorization) {

        if (configCenter.getSystemConfig().isEnableCrossOrigin()) {
            Set<String> trustDomains = configCenter.getGatewayConfig().getTrustDomains();

            String domain = authorization.getDomain();
            if (Strings.isEmpty(domain)) {
                return;
            }
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
}
