package com.ziyao.ideal.uaa.authentication.strategy;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.User;
import com.ziyao.ideal.uaa.authentication.token.FailureAuthenticationToken;
import com.ziyao.ideal.uaa.authentication.token.UsernamePasswordAuthenticationToken;
import com.ziyao.ideal.uaa.common.exception.InvalidUserStateException;
import org.springframework.stereotype.Component;

/**
 * @author ziyao zhang
 */
@Component
public class InvalidUserStateFailureStrategy implements AuthenticationFailureStrategy {

    @Override
    public Authentication handleFailure(Authentication authentication, Exception exception) {

        if (!(exception instanceof InvalidUserStateException)) {
            return null;
        }

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;

        User user = (User) authenticationToken.getPrincipal();

        return FailureAuthenticationToken.of(500, exception.getMessage());
    }

    @Override
    public boolean isSupport(Class<? extends Exception> exceptionClass) {
        return InvalidUserStateException.class.isAssignableFrom(exceptionClass);
    }

}
