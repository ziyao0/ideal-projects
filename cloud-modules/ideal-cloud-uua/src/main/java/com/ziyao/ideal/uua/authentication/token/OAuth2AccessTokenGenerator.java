package com.ziyao.ideal.uua.authentication.token;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Maps;
import com.ziyao.ideal.crypto.keygen.Base64StringKeyGenerator;
import com.ziyao.ideal.crypto.keygen.StringKeyGenerator;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.oauth2.core.ClaimAccessor;
import com.ziyao.ideal.security.oauth2.core.OAuth2AccessToken;
import com.ziyao.ideal.security.oauth2.core.OAuth2TokenType;
import com.ziyao.ideal.security.oauth2.core.RegisteredApp;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2ParameterNames;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2TokenClaimsSet;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2TokenContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.*;

/**
 * @author ziyao zhang
 */
public final class OAuth2AccessTokenGenerator implements OAuth2TokenGenerator<OAuth2AccessToken> {

    private static final Logger log = LoggerFactory.getLogger(OAuth2AccessTokenGenerator.class);

    private final StringKeyGenerator accessTokenGenerator = new Base64StringKeyGenerator(
            Base64.getUrlEncoder().withoutPadding(), 32);


    @Override
    public OAuth2AccessToken generate(OAuth2TokenContext context) {
        if (!OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
            return null;
        }
        RegisteredApp registeredApp = context.getRegisteredApp();

        String issuer = registeredApp.getRedirectUri();

        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plus(registeredApp.getTokenSettings().getAccessTokenTimeToLive());

        OAuth2TokenClaimsSet.Builder claimsBuilder = OAuth2TokenClaimsSet.builder();

        if (Strings.hasText(issuer)) {
            claimsBuilder.issuer(issuer);
        }

        claimsBuilder
                .subject(registeredApp.getAppName())
                .audience(Collections.singletonList(String.valueOf(registeredApp.getAppId())))
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .notBefore(issuedAt)
                .id(UUID.randomUUID().toString());

        if (!CollectionUtils.isEmpty(context.getAuthorizedScopes())) {
            claimsBuilder.claim(OAuth2ParameterNames.SCOPE, context.getAuthorizedScopes());
        }


        OAuth2TokenClaimsSet accessTokenClaimsSet = claimsBuilder.build();

        OAuth2AccessToken accessToken = new OAuth2AccessTokenClaims(OAuth2AccessToken.TokenType.Bearer,
                // TODO accessTokenClaimsSet.getClaims()
                this.accessTokenGenerator.generateKey(), accessTokenClaimsSet.getIssuedAt(),
                accessTokenClaimsSet.getExpiresAt(), context.getAuthorizedScopes(), Maps.newHashMap());
        if (log.isDebugEnabled()) {
            log.debug("generate access token to {}", JSON.toJSONString(accessToken));
        }
        return accessToken;
    }


    public static final class OAuth2AccessTokenClaims extends OAuth2AccessToken implements ClaimAccessor {

        private final Map<String, Object> claims;

        private OAuth2AccessTokenClaims(TokenType tokenType, String tokenValue, Instant issuedAt, Instant expiresAt,
                                        Set<String> scopes, Map<String, Object> claims) {
            super(tokenType, tokenValue, issuedAt, expiresAt, scopes);
            this.claims = claims;
        }

        @Override
        public Map<String, Object> getClaims() {
            return this.claims;
        }
    }
}
