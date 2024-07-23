package com.ziyao.harbor.usercenter.authentication.strategy;

import com.ziyao.security.oauth2.core.Authentication;

/**
 * @author ziyao zhang
 */
public interface AuthenticationStrategyManager {


    Authentication handle(Authentication authentication, Exception ex);
}
