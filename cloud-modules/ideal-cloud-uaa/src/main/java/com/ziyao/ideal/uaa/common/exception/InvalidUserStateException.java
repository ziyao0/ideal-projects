package com.ziyao.ideal.uaa.common.exception;

import com.ziyao.ideal.security.core.AuthenticationException;

import java.io.Serial;

/**
 * @author ziyao zhang
 */
public class InvalidUserStateException extends AuthenticationException {

    @Serial
    private static final long serialVersionUID = 7363220172525003980L;

    public InvalidUserStateException(String message) {
        super(message);
    }
}
