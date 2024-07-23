package com.ziyao.gateway.core;

import com.ziyao.gateway.core.token.AccessToken;
import com.ziyao.gateway.core.token.Authorization;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 默认授权方
 *
 * @author ziyao zhang
 */
@Component
public class DefaultAuthorizer implements Authorizer {

    @Override
    public Mono<Authorization> authorize(AccessToken accessToken) {

//        String token = accessToken.getToken();
//        try {
//            Map<String, Claim> claims = Jwts.getClaims(token, SecretUtils.loadJwtTokenSecret());
//            return Mono.just(new SuccessAuthorization(claims, token));
//        } catch (JWTVerificationException e) {
//            return Mono.just(new FailureAuthorization(e.getMessage()));
//        }
        return null;
    }
}
