package com.ziyao.ideal.uua.authentication.strategy;

import com.ziyao.ideal.security.core.Authentication;

/**
 * @author ziyao zhang
 */
public interface AuthenticationStrategyManager {


    Authentication handle(Authentication authentication, Exception ex);
}
