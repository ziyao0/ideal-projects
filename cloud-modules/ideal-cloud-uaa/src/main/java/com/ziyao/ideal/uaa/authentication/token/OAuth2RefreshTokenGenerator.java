package com.ziyao.ideal.uaa.authentication.token;

import com.ziyao.ideal.crypto.keygen.Base64StringKeyGenerator;
import com.ziyao.ideal.crypto.keygen.StringKeyGenerator;
import com.ziyao.ideal.security.oauth2.core.OAuth2RefreshToken;
import com.ziyao.ideal.security.oauth2.core.OAuth2TokenType;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2TokenContext;

import java.time.Instant;
import java.util.Base64;

/**
 * @author ziyao zhang
 */
public final class OAuth2RefreshTokenGenerator implements OAuth2TokenGenerator<OAuth2RefreshToken> {


    private final StringKeyGenerator refreshTokenGenerator = new Base64StringKeyGenerator(
            Base64.getUrlEncoder().withoutPadding(), 32);


    @Override
    public OAuth2RefreshToken generate(OAuth2TokenContext context) {
        if (!OAuth2TokenType.REFRESH_TOKEN.equals(context.getTokenType())) {
            return null;
        }

        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plus(context.getRegisteredApp().getTokenSettings().getRefreshTokenTimeToLive());
        return new OAuth2RefreshToken(this.refreshTokenGenerator.generateKey(), issuedAt, expiresAt);
    }
}
