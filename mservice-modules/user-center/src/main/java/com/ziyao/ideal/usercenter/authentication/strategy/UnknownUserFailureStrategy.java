package com.ziyao.ideal.usercenter.authentication.strategy;

import com.ziyao.ideal.usercenter.common.exception.UnknownUserException;
import com.ziyao.ideal.security.core.Authentication;
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
