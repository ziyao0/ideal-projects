package com.ziyao.ideal.usercenter.authentication;

import com.ziyao.security.oauth2.core.Authentication;

/**
 * @author ziyao zhang
 */
public interface AuthenticationHandler {

    Authentication onSuccessful(Authentication authentication);

    Authentication onFailure(Authentication authentication, Exception ex);

}
