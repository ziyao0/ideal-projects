package com.ziyao.ideal.uaa.common.exception;

import com.ziyao.ideal.security.core.AuthenticationException;


/**
 * @author ziyao zhang
 */
public class UnknownUserException extends AuthenticationException {


    private static final long serialVersionUID = 7363220172525003980L;

    public UnknownUserException(String message) {
        super(message);
    }
}
