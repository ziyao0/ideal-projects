package com.ziyao.ideal.usercenter.service.security;

import com.ziyao.security.oauth2.core.Authentication;

/**
 * @author ziyao zhang
 */
public interface AuthenticationService {


    Authentication login(Authentication authentication);

}
