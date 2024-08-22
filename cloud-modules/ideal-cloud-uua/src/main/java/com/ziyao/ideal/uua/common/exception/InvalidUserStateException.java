package com.ziyao.ideal.uua.common.exception;

import com.ziyao.ideal.security.core.AuthenticationException;


/**
 * @author ziyao zhang
 */
public class InvalidUserStateException extends AuthenticationException {


    private static final long serialVersionUID = 7363220172525003980L;

    public InvalidUserStateException(String message) {
        super(message);
    }
}
