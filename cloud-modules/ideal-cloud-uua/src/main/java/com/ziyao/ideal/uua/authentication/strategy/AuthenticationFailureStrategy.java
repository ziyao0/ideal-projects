package com.ziyao.ideal.uua.authentication.strategy;

import com.ziyao.ideal.web.response.ResponseDetails;
import com.ziyao.ideal.uua.authentication.token.FailureAuthentication;
import com.ziyao.ideal.security.core.Authentication;

/**
 * @author ziyao zhang
 */
@FunctionalInterface
public interface AuthenticationFailureStrategy {

    Authentication handleFailure(Authentication authentication, Exception exception);

    default Authentication createFailureAuthentication(Integer status, String message) {
        return FailureAuthentication.of(status, message);
    }

    default Authentication createFailureAuthentication(ResponseDetails responseDetails) {
        return FailureAuthentication.of(responseDetails);
    }

    default Authentication createFailureAuthentication(ResponseDetails sm, Object data) {
        return FailureAuthentication.of(sm.getStatus(), sm.getMessage(), data);
    }

    default Authentication createFailureAuthentication(int status, String message, Object data) {
        return FailureAuthentication.of(status, message, data);
    }
}
