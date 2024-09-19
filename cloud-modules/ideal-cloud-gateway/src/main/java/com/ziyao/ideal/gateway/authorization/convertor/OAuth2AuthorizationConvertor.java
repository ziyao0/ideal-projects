package com.ziyao.ideal.gateway.authorization.convertor;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.gateway.authorization.Authorization;
import com.ziyao.ideal.gateway.authorization.OAuth2AuthorizationToken;
import com.ziyao.ideal.gateway.core.RequestAttributes;
import com.ziyao.ideal.gateway.support.IpUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class OAuth2AuthorizationConvertor implements AuthorizationConvertor {
    @Override
    public Authorization convert(ServerWebExchange exchange) {

        HttpHeaders headers = exchange.getRequest().getHeaders();

        if ()

        String marking = headers.getFirst(RequestAttributes.OAUTH_REQUEST_MARKING);
        if (Strings.isEmpty(marking)) {
            return null;
        }
        String token = headers.getFirst(RequestAttributes.AUTHORIZATION);

        // @formatter:off
        return OAuth2AuthorizationToken.withAccessToken(token)
                .refreshToken(headers.getFirst(RequestAttributes.REFRESH_TOKEN))
                .timestamp(headers.getFirstDate(RequestAttributes.TIMESTAMP))
                .digest(RequestAttributes.DIGEST)
                .resource(RequestAttributes.RESOURCE)
//                .requestPath(exchange.getAttributes().get(ServerWebExchangeUtils.GATEWAY_PREDICATE_PATH_CONTAINER_ATTR).toString())
                .requestPath(exchange.getRequest().getPath().value())
                .ip(IpUtils.getIP(exchange))
                .build();
        // @formatter:on
    }
}
