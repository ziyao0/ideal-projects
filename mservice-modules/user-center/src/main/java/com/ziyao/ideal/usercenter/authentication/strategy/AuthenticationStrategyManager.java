package com.ziyao.ideal.usercenter.authentication.strategy;

import com.ziyao.security.oauth2.core.Authentication;

/**
 * @author ziyao zhang
 */
public interface AuthenticationStrategyManager {


    Authentication handle(Authentication authentication, Exception ex);
}
