package com.ziyao.ideal.uua.authentication.token;


import com.ziyao.ideal.security.oauth2.core.OAuth2Token;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2TokenContext;

/**
 * @author ziyao zhang
 */
@FunctionalInterface
public interface OAuth2TokenGenerator<T extends OAuth2Token> {


    T generate(OAuth2TokenContext context);
}
