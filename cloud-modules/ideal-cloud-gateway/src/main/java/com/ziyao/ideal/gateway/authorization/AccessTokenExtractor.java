package com.ziyao.ideal.gateway.authorization;

import com.ziyao.ideal.gateway.authorization.support.RequestAttributes;
import com.ziyao.ideal.gateway.authorization.token.AccessToken;
import com.ziyao.ideal.gateway.authorization.token.DefaultAccessToken;
import com.ziyao.ideal.gateway.support.IpUtils;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author zhangziyao
 */
public abstract class AccessTokenExtractor {


    /**
     * 从请求头提取认证token
     *
     * @param exchange {@link ServerWebExchange}
     * @return 返回认证token
     * @see AccessToken
     */
    public static DefaultAccessToken extractForHeaders(ServerWebExchange exchange) {
        return doExtracted(exchange);
    }

    public static DefaultAccessToken doExtracted(ServerWebExchange exchange) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String token = headers.getFirst(RequestAttributes.AUTHORIZATION);

        // @formatter:off
        return DefaultAccessToken.builder()
                .token(token)
                .refreshToken(headers.getFirst(RequestAttributes.REFRESH_TOKEN))
                .timestamp(headers.getFirst(RequestAttributes.TIMESTAMP))
                .digest(RequestAttributes.DIGEST)
                .resource(RequestAttributes.RESOURCE)
                .api(exchange.getAttributes().get(ServerWebExchangeUtils.GATEWAY_PREDICATE_PATH_CONTAINER_ATTR).toString())
                .ip(IpUtils.getIP(exchange))
                .name("accessControl")
                .build();
        // @formatter:on
    }

    private AccessTokenExtractor() {
    }
}
