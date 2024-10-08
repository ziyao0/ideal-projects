package com.ziyao.ideal.uaa.authentication.token;

import com.ziyao.ideal.security.oauth2.core.Token;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class AccessToken implements Token {

    private final String token;

    public AccessToken(String token) {
        this.token = token;
    }

    @Override
    public String getTokenValue() {
        return token;
    }
}
