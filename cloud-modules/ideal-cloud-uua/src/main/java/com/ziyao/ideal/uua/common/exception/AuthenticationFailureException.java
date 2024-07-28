package com.ziyao.ideal.uua.common.exception;

import com.ziyao.ideal.web.response.ResponseMetadata;
import com.ziyao.ideal.security.core.AuthenticationException;

import java.io.Serial;

/**
 * @author ziyao zhang
 */
public class AuthenticationFailureException extends AuthenticationException implements ResponseMetadata {
    @Serial
    private static final long serialVersionUID = 2065959447074513639L;

    private final ResponseMetadata responseMetadata;


    public AuthenticationFailureException(String message) {
        super(message);
        this.responseMetadata = ResponseMetadata.getInstance(401, message);
    }

    public AuthenticationFailureException(int code, String message) {
        super(message);
        this.responseMetadata = ResponseMetadata.getInstance(code, message);
    }

    public AuthenticationFailureException(ResponseMetadata responseMetadata) {
        super(responseMetadata.getMessage());
        this.responseMetadata = responseMetadata;
    }

    @Override
    public Integer getStatus() {
        return responseMetadata.getStatus();
    }
}
