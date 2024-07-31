package com.ziyao.ideal.uua.authentication.strategy;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.UserInfo;
import com.ziyao.ideal.uua.authentication.token.FailureAuthenticationToken;
import com.ziyao.ideal.uua.authentication.token.UsernamePasswordAuthenticationToken;
import com.ziyao.ideal.uua.common.exception.InvalidUserStateException;
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

        UserInfo principal = (UserInfo) authenticationToken.getPrincipal();

        return FailureAuthenticationToken.of(500, exception.getMessage());
    }

    @Override
    public boolean isSupport(Class<? extends Exception> exceptionClass) {
        return InvalidUserStateException.class.isAssignableFrom(exceptionClass);
    }

}
