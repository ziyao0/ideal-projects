package com.ziyao.ideal.gateway.support;

import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.gateway.security.AuthorizationSuccessful;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;

/**
 * @author ziyao
 */
public abstract class HeadersInjector {

    public static void inject(ServerWebExchange exchange, AuthorizationSuccessful authorization) {
        Assert.notNull(exchange, "缺少注入对象(ServerWebExchange)");
        Assert.notNull(authorization, "缺少要注入的信息(Authorization)");
        exchange.getRequest().mutate()
                .headers(httpHeaders -> {
                    MultiValueMap<String, String> headers = new HttpHeaders();
                    for (Map.Entry<String, Object> entry : authorization.getClaims().entrySet()) {
                        String value = Strings.encodeURLUTF8(entry.getValue().toString());
                        headers.add(entry.getKey(), value);
                    }
                    httpHeaders.addAll(headers);
                })
                .build();
    }

    private HeadersInjector() {
    }
}