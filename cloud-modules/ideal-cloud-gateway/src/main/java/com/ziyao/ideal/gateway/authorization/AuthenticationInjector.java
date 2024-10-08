//package com.ziyao.ideal.gateway.authorization;
//
//import com.ziyao.ideal.core.Assert;
//import com.ziyao.ideal.core.Dates;
//import com.ziyao.ideal.core.Strings;
//import com.ziyao.ideal.gateway.core.RequestAttributes;
//import com.ziyao.ideal.security.core.GrantedAuthority;
//import com.ziyao.ideal.security.core.User;
//import com.ziyao.ideal.security.core.UserParamNames;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//
//import java.util.Optional;
//import java.util.stream.Collectors;
//
///**
// * @author ziyao
// */
//@Component
//public class AuthenticationInjector implements HeadersInjector {
//
//
//    @Override
//    public void inject(ServerWebExchange exchange) {
//
//        Assert.notNull(exchange, "缺少注入对象(ServerWebExchange)");
//        AuthorizationToken authorizationToken = RequestAttributes.loadAuthorizationToken(exchange);
//        Optional<User> principalOptional = authorizationToken.getPrincipal();
//
//        principalOptional.ifPresent(principal -> {
//            exchange.getRequest().mutate()
//                    .header(HttpHeaders.AUTHORIZATION, authorizationToken.getToken())
//                    .header(UserParamNames.USER_ID, Strings.encodeURLUTF8(principal.getId().toString()))
//                    .header(UserParamNames.USERNAME, Strings.encodeURLUTF8(principal.getUsername()))
//                    .header(UserParamNames.STATUS, Strings.encodeURLUTF8(String.valueOf(principal.getStatus())))
//                    .header(UserParamNames.ID_CARD_NAME, Strings.encodeURLUTF8(principal.getIdCardName()))
//                    .header(UserParamNames.GENDER, Strings.encodeURLUTF8(principal.getGender()))
//                    .header(UserParamNames.MOBILE, Strings.encodeURLUTF8(principal.getMobile()))
//                    .header(UserParamNames.ADDRESS, Strings.encodeURLUTF8(principal.getAddress()))
//                    .header(UserParamNames.LAST_LOGIN, Strings.encodeURLUTF8(Dates.format(principal.getLastLogin())))
//                    .header(UserParamNames.LOGIN_IP, Strings.encodeURLUTF8(principal.getLoginIp()))
//                    .header(UserParamNames.AUTHORITIES, Strings.encodeURLUTF8(
//                            Strings.collectionToCommaDelimitedString(principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))))
//                    .header(UserParamNames.CURRENT_IP, authorizationToken.getIp())
//                    .build();
//        });
//
//    }
//
//}
