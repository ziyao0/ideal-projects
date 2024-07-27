package com.ziyao.ideal.usercenter.authentication.strategy;

import com.ziyao.ideal.web.response.ResponseMetadata;
import com.ziyao.ideal.usercenter.authentication.token.FailureAuthentication;
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

    default Authentication createFailureAuthentication(ResponseMetadata responseMetadata) {
        return FailureAuthentication.of(responseMetadata);
    }

    default Authentication createFailureAuthentication(ResponseMetadata sm, Object data) {
        return FailureAuthentication.of(sm.getStatus(), sm.getMessage(), data);
    }

    default Authentication createFailureAuthentication(int status, String message, Object data) {
        return FailureAuthentication.of(status, message, data);
    }
}
