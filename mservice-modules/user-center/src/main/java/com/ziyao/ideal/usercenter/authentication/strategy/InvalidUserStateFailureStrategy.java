package com.ziyao.ideal.usercenter.authentication.strategy;

import com.ziyao.ideal.usercenter.common.exception.InvalidUserStateException;
import com.ziyao.security.oauth2.core.Authentication;

/**
 * @author ziyao zhang
 */
public class InvalidUserStateFailureStrategy implements AuthenticationFailureStrategy {
    @Override
    public Authentication handleFailure(Authentication authentication, Exception exception) {

        if (!(exception instanceof InvalidUserStateException)) {
            return null;
        }

        return null;
    }
}
