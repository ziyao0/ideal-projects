package com.ziyao.ideal.gateway.filter.intercept;

import com.ziyao.ideal.core.Assert;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ziyao zhang
 */
@Getter
public class DelegatingInterceptor implements GatewayInterceptor {

    private final List<GatewayInterceptor> gatewayInterceptors;

    public DelegatingInterceptor(GatewayInterceptor... gatewayInterceptors) {
        Assert.notNull(gatewayInterceptors, "interceptors cannot be empty");
        Assert.noNullElements(gatewayInterceptors, "interceptors cannot be null");
        this.gatewayInterceptors = List.copyOf(asList(gatewayInterceptors));
    }

    @Override
    public void intercept(InterceptContext context) {
        for (GatewayInterceptor gatewayInterceptor : getGatewayInterceptors()) {
            gatewayInterceptor.intercept(context);
        }
    }


    private List<GatewayInterceptor> asList(
            GatewayInterceptor... gatewayInterceptors) {

        List<GatewayInterceptor> gatewayInterceptorList = List.copyOf(Arrays.asList(gatewayInterceptors));
        return gatewayInterceptorList.stream()
                .sorted(Comparator.comparing(GatewayInterceptor::getOrder))
                .collect(Collectors.toList());
    }
}
