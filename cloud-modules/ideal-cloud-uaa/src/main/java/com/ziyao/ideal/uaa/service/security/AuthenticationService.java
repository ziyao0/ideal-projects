package com.ziyao.ideal.uaa.service.security;

import com.ziyao.ideal.security.core.Authentication;

/**
 * @author ziyao zhang
 */
public interface AuthenticationService {


    Authentication login(Authentication authentication);

}
