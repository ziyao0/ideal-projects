package com.ziyao.ideal.security.core;



/**
 * @author ziyao
 */
public abstract class AuthenticationException extends RuntimeException {

    
    private static final long serialVersionUID = -5592880436851278628L;

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
