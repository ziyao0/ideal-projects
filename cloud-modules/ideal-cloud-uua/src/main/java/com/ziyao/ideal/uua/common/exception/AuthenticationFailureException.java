package com.ziyao.ideal.uua.common.exception;

import com.ziyao.ideal.web.response.ResponseDetails;
import com.ziyao.ideal.security.core.AuthenticationException;

import java.io.Serial;

/**
 * @author ziyao zhang
 */
public class AuthenticationFailureException extends AuthenticationException implements ResponseDetails {
    @Serial
    private static final long serialVersionUID = 2065959447074513639L;

    private final ResponseDetails responseDetails;


    public AuthenticationFailureException(String message) {
        super(message);
        this.responseDetails = ResponseDetails.getInstance(401, message);
    }

    public AuthenticationFailureException(int code, String message) {
        super(message);
        this.responseDetails = ResponseDetails.getInstance(code, message);
    }

    public AuthenticationFailureException(ResponseDetails responseDetails) {
        super(responseDetails.getMessage());
        this.responseDetails = responseDetails;
    }

    @Override
    public Integer getStatus() {
        return responseDetails.getStatus();
    }
}
