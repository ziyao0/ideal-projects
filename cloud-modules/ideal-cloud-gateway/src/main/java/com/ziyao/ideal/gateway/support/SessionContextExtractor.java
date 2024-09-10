package com.ziyao.ideal.gateway.support;

import com.ziyao.ideal.gateway.common.response.RequestAttributes;
import com.ziyao.ideal.gateway.security.DefaultSessionContext;
import com.ziyao.ideal.gateway.security.SessionContext;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author zhangziyao
 */
public abstract class SessionContextExtractor {


    /**
     * 从请求头提取认证token
     *
     * @param exchange {@link ServerWebExchange}
     * @return 返回认证token
     * @see SessionContext
     */
    public static DefaultSessionContext extractForHeaders(ServerWebExchange exchange) {
        return doExtracted(exchange);
    }

    public static DefaultSessionContext doExtracted(ServerWebExchange exchange) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String token = headers.getFirst(RequestAttributes.AUTHORIZATION);

        // @formatter:off
        return DefaultSessionContext.builder()
                .token(token)
                .refreshToken(headers.getFirst(RequestAttributes.REFRESH_TOKEN))
                .timestamp(headers.getFirst(RequestAttributes.TIMESTAMP))
                .digest(RequestAttributes.DIGEST)
                .resourceUri(RequestAttributes.RESOURCE)
                .requestUri(exchange.getAttributes().get(ServerWebExchangeUtils.GATEWAY_PREDICATE_PATH_CONTAINER_ATTR).toString())
                .ip(IpUtils.getIP(exchange))
                .build();
        // @formatter:on
    }

    private SessionContextExtractor() {
    }
}
