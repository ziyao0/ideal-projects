package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.core.DateUtils;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.gateway.authorization.Authorization;
import com.ziyao.ideal.gateway.authorization.AuthorizationManager;
import com.ziyao.ideal.gateway.authorization.AuthorizationToken;
import com.ziyao.ideal.gateway.authorization.convertor.AuthorizationConvertor;
import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.config.SystemConfig;
import com.ziyao.ideal.gateway.core.RequestAttributes;
import com.ziyao.ideal.gateway.core.error.GatewayErrors;
import com.ziyao.ideal.gateway.intercept.RequestInterceptor;
import com.ziyao.ideal.gateway.support.IpUtils;
import com.ziyao.ideal.gateway.support.SecurityUtils;
import com.ziyao.ideal.security.core.User;
import com.ziyao.ideal.security.core.UserParamNames;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * grant
 *
 * @author ziyao zhang
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends AbstractGlobalFilter {

    private final ConfigCenter configCenter;
    private final AuthorizationConvertor authorizationConvertor;
    private final AuthorizationManager authorizationManager;
    private final RequestInterceptor requestInterceptor;

    @Override
    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        SystemConfig systemConfig = configCenter.getSystemConfig();
        // 从请求头提取认证 token
        Authorization authorization = authorizationConvertor.convert(exchange);
        // 过滤黑名单、跨域等相关信息
        requestInterceptor.intercept(authorization);
        AuthorizationToken authorizationToken = (AuthorizationToken) authorizationManager.authorize(authorization);

        inject(exchange, authorizationToken);
        RequestAttributes.storeAuthorizationToken(exchange, authorizationToken);
        return SecurityUtils.authorized(authorizationToken)
                ? chain.filter(exchange)
                : systemConfig.isEnablePermissionVerify()
                ? GatewayErrors.createException(authorizationToken.getResponse())
                : chain.filter(exchange);
    }

    private void inject(ServerWebExchange exchange, AuthorizationToken authorizationToken) {

        Optional<User> principalOptional = authorizationToken.getPrincipal();

        principalOptional.ifPresent(principal -> {
            exchange.getRequest().mutate()
                    .headers(headers -> {
                        headers.set(UserParamNames.USER_ID, Strings.encodeURLUTF8(principal.getId().toString()));
                        headers.set(UserParamNames.USERNAME, Strings.encodeURLUTF8(principal.getUsername()));
                        headers.set(UserParamNames.NICKNAME, Strings.encodeURLUTF8(principal.getNickname()));
                        headers.set(UserParamNames.STATUS, Strings.encodeURLUTF8(String.valueOf(principal.getStatus())));
                        headers.set(UserParamNames.ID_CARD_NAME, Strings.encodeURLUTF8(principal.getIdCardName()));
                        headers.set(UserParamNames.GENDER, Strings.encodeURLUTF8(principal.getGender()));
                        headers.set(UserParamNames.MOBILE, Strings.encodeURLUTF8(principal.getMobile()));
                        headers.set(UserParamNames.ADDRESS, Strings.encodeURLUTF8(principal.getAddress()));
                        if (principal.getLastLogin() != null) {
                            headers.set(UserParamNames.LAST_LOGIN, Strings.encodeURLUTF8(DateUtils.format(principal.getLastLogin())));
                        }
                        headers.set(UserParamNames.LOGIN_IP, Strings.encodeURLUTF8(principal.getLoginIp()));
                        //TODO
//                        Set<String> authorities = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
//                        headers.set(UserParamNames.AUTHORITIES, Strings.encodeURLUTF8(Strings.collectionToCommaDelimitedString(authorities)));
                    })
                    .build();
        });
        exchange.getRequest().mutate().headers(headers -> {
            headers.set(UserParamNames.IP, Strings.encodeURLUTF8(authorizationToken.getIp()));
            headers.set(UserParamNames.LOCATION, Strings.encodeURLUTF8(IpUtils.getAddr(authorizationToken.getIp())));
        }).build();

    }

    @Override
    public int getOrder() {
        return FilterOrder.Authorization.getOrder();
    }
}
