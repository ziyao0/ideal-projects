package com.ziyao.harbor.usercenter.authentication.strategy;

import com.ziyao.harbor.usercenter.common.exception.UnknownUserException;
import com.ziyao.security.oauth2.core.Authentication;
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
}
