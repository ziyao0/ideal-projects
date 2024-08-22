package com.ziyao.ideal.uaa.authentication.token;

import com.ziyao.ideal.crypto.keygen.Base64StringKeyGenerator;
import com.ziyao.ideal.crypto.keygen.StringKeyGenerator;
import com.ziyao.ideal.security.oauth2.core.OAuth2AuthorizationCode;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2ParameterNames;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2TokenContext;

import java.time.Instant;
import java.util.Base64;

/**
 * @author ziyao zhang
 */
public final class OAuth2AuthorizationCodeGenerator implements OAuth2TokenGenerator<OAuth2AuthorizationCode> {

    private final StringKeyGenerator authorizationCodeGenerator = new Base64StringKeyGenerator(
            Base64.getUrlEncoder().withoutPadding(), 16);


    @Override
    public OAuth2AuthorizationCode generate(OAuth2TokenContext context) {
        if (context.getTokenType() == null || !OAuth2ParameterNames.CODE.equals(context.getTokenType().value())) {
            return null;
        }
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt
                .plus(context.getRegisteredApp().getTokenSettings().getAuthorizationCodeTimeToLive());
        return new OAuth2AuthorizationCode(this.authorizationCodeGenerator.generateKey(), issuedAt, expiresAt);
    }

}
