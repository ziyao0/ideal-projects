package com.ziyao.gateway.core;

import com.auth0.jwt.interfaces.Claim;
import com.ziyao.eis.core.Assert;
import com.ziyao.eis.core.Strings;
import com.ziyao.gateway.core.token.SuccessAuthorization;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;

/**
 * @author ziyao
 */
public abstract class HeadersInjector {

    public static void inject(ServerWebExchange exchange, SuccessAuthorization authorization) {
        Assert.notNull(exchange, "缺少注入对象(ServerWebExchange)");
        Assert.notNull(authorization, "缺少要注入的信息(Authorization)");
        exchange.getRequest().mutate()
                .headers(httpHeaders -> {
                    MultiValueMap<String, String> headers = new HttpHeaders();
                    for (Map.Entry<String, Claim> entry : authorization.getClaims().entrySet()) {
                        String value = Strings.encodeURLUTF8(entry.getValue().as(Object.class).toString());
                        headers.add(entry.getKey(), value);
                    }
                    httpHeaders.addAll(headers);
                })
                .build();
    }

    private HeadersInjector() {
    }
}
