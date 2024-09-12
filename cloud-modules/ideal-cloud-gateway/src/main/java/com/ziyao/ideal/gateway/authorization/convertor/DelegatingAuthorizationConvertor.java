package com.ziyao.ideal.gateway.authorization.convertor;

import com.ziyao.ideal.gateway.authorization.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

/**
 * @author ziyao zhang
 */
@RequiredArgsConstructor
public class DelegatingAuthorizationConvertor implements AuthorizationConvertor {

    private final List<AuthorizationConvertor> convertors;

    @Override
    public Authorization convert(ServerWebExchange exchange) {
        for (AuthorizationConvertor authorizationConvertor : this.convertors) {
            Authorization authorization = authorizationConvertor.convert(exchange);
            if (authorization != null) {
                return authorization;
            }
        }
        return null;
    }
}
