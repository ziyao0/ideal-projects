package com.ziyao.ideal.gateway.intercept;

import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.gateway.authorization.Authorization;
import lombok.Getter;

import java.util.List;

/**
 * @author ziyao zhang
 */
@Getter
public class DelegatingInterceptor implements RequestInterceptor {

    private final List<RequestInterceptor> interceptors;

    public DelegatingInterceptor(List<RequestInterceptor> requestInterceptors) {
        Assert.notNull(requestInterceptors, "interceptors cannot be empty");
        this.interceptors = List.copyOf(requestInterceptors);
    }

    @Override
    public void intercept(Authorization authorization) {
        for (RequestInterceptor requestInterceptor : getInterceptors()) {
            requestInterceptor.intercept(authorization);
        }
    }
}
