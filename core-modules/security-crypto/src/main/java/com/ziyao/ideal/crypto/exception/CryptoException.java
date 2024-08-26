package com.ziyao.ideal.crypto.exception;

import java.io.Serial;

/**
 * @author ziyao
 * 
 */
public class CryptoException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 7228570590120864863L;

    public CryptoException(String message) {
        super(message);
    }


    public CryptoException(Throwable e) {
        super(e);
    }

}
