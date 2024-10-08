package com.ziyao.ideal.uaa.authentication.token;

import com.ziyao.ideal.crypto.keygen.Base64StringKeyGenerator;
import com.ziyao.ideal.crypto.keygen.StringKeyGenerator;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2TokenContext;

import java.util.Base64;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class AccessTokenGenerator implements TokenGenerator<AccessToken> {

    private final StringKeyGenerator accessTokenGenerator = new Base64StringKeyGenerator(
            Base64.getUrlEncoder().withoutPadding(), 32);


    @Override
    public AccessToken generate(OAuth2TokenContext context) {

        String tokenValue = this.accessTokenGenerator.generateKey();

        return new AccessToken(tokenValue);
    }
}
