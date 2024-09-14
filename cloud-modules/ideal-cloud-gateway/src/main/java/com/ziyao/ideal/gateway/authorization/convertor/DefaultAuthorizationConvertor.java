package com.ziyao.ideal.gateway.authorization.convertor;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.gateway.authorization.Authorization;
import com.ziyao.ideal.gateway.authorization.DefaultAuthorizationToken;
import com.ziyao.ideal.gateway.core.RequestAttributes;
import com.ziyao.ideal.gateway.support.IpUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class DefaultAuthorizationConvertor implements AuthorizationConvertor {


    @Override
    public Authorization convert(ServerWebExchange exchange) {

        HttpHeaders headers = exchange.getRequest().getHeaders();
        String marking = headers.getFirst(RequestAttributes.OAUTH_REQUEST_MARKING);
        if (Strings.hasText(marking)) {
            return null;
        }
        String token = headers.getFirst(RequestAttributes.AUTHORIZATION);

        return DefaultAuthorizationToken.withAccessToken(token)
                .resource(RequestAttributes.RESOURCE)
//                .requestPath(exchange.getAttributes().get(ServerWebExchangeUtils.GATEWAY_PREDICATE_PATH_CONTAINER_ATTR).toString())
                .requestPath(exchange.getRequest().getPath().toString())
                .ip(IpUtils.getIP(exchange))
                .build();
    }
}
