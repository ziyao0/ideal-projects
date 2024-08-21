package com.ziyao.ideal.gateway.support;

import com.ziyao.ideal.gateway.common.response.RequestAttributes;
import com.ziyao.ideal.gateway.security.DefaultGSecurityContext;
import com.ziyao.ideal.gateway.security.GSecurityContext;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author zhangziyao
 */
public abstract class GSecurityContextExtractor {


    /**
     * 从请求头提取认证token
     *
     * @param exchange {@link ServerWebExchange}
     * @return 返回认证token
     * @see GSecurityContext
     */
    public static DefaultGSecurityContext extractForHeaders(ServerWebExchange exchange) {
        return doExtracted(exchange);
    }

    public static DefaultGSecurityContext doExtracted(ServerWebExchange exchange) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String token = headers.getFirst(RequestAttributes.AUTHORIZATION);

        // @formatter:off
        return DefaultGSecurityContext.builder()
                .token(token)
                .refreshToken(headers.getFirst(RequestAttributes.REFRESH_TOKEN))
                .timestamp(headers.getFirst(RequestAttributes.TIMESTAMP))
                .digest(RequestAttributes.DIGEST)
                .resourceUri(RequestAttributes.RESOURCE)
                .requestUri(exchange.getAttributes().get(ServerWebExchangeUtils.GATEWAY_PREDICATE_MATCHED_PATH_ATTR).toString())
                .ip(IpUtils.getIP(exchange))
                .name("accessControl")
                .build();
        // @formatter:on
    }

    private GSecurityContextExtractor() {
    }
}
