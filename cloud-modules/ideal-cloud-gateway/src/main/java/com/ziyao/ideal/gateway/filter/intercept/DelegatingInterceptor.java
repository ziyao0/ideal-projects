package com.ziyao.ideal.gateway.filter.intercept;

import com.google.common.collect.Lists;
import com.ziyao.ideal.core.Assert;
import lombok.Getter;

import java.util.List;

/**
 * @author ziyao zhang
 */
@Getter
public class DelegatingInterceptor implements GatewayInterceptor {

    private final List<GatewayInterceptor> interceptors;

    public DelegatingInterceptor(List<GatewayInterceptor> gatewayInterceptors) {
        Assert.notNull(gatewayInterceptors, "interceptors cannot be empty");
        this.interceptors = Lists.newArrayList(gatewayInterceptors);
    }

    @Override
    public void intercept(InterceptContext context) {
        for (GatewayInterceptor gatewayInterceptor : getInterceptors()) {
            gatewayInterceptor.intercept(context);
        }
    }
}
