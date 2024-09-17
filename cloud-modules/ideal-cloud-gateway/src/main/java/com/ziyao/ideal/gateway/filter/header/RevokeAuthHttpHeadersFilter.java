package com.ziyao.ideal.gateway.filter.header;

import com.ziyao.ideal.gateway.core.RequestAttributes;
import com.ziyao.ideal.gateway.filter.FilterOrder;
import org.springframework.cloud.gateway.filter.headers.HttpHeadersFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * 重置请求头
 * <p>
 * 删除认证相关的请求头
 *
 * @author ziyao zhang
 */
@Component
public class RevokeAuthHttpHeadersFilter implements HttpHeadersFilter, Ordered {

    @Override
    public HttpHeaders filter(HttpHeaders input, ServerWebExchange exchange) {

        HttpHeaders httpHeaders = new HttpHeaders();
        // @formatter:off
        input.entrySet().stream()
                .filter(entry -> !RequestAttributes.AUTHORIZATION_HEADERS.contains(entry.getKey()))
                .forEach(entry -> httpHeaders.addAll(entry.getKey(), entry.getValue()));

        return httpHeaders;
    }

    @Override
    public boolean supports(Type type) {
        return HttpHeadersFilter.super.supports(type);
    }

    @Override
    public int getOrder() {
        return FilterOrder.RevokeAuthHttpHeaders.getOrder();
    }
}
