package com.ziyao.ideal.uaa.authentication.strategy;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.uaa.common.exception.UnknownUserException;
import org.springframework.stereotype.Component;

/**
 * @author ziyao zhang
 */
@Component
public class UnknownUserFailureStrategy implements AuthenticationFailureStrategy {
    @Override
    public Authentication handleFailure(Authentication authentication, Exception exception) {

        if (!(exception instanceof UnknownUserException)) {
            return null;
        }
        return null;
    }

    @Override
    public boolean isSupport(Class<? extends Exception> exceptionClass) {
        return UnknownUserException.class.isAssignableFrom(exceptionClass);
    }
}
