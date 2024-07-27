package com.ziyao.ideal.security.oauth2.core.token.validators;


import com.ziyao.ideal.security.oauth2.core.OAuth2Token;

/**
 * @author ziyao zhang
 */
public interface OAuth2TokenValidator<T extends OAuth2Token> {

    void validate(T token);
}
