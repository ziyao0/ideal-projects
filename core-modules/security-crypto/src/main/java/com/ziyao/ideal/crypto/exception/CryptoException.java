package com.ziyao.ideal.crypto.exception;



/**
 * @author ziyao
 * 
 */
public class CryptoException extends RuntimeException {
    
    private static final long serialVersionUID = 7228570590120864863L;

    public CryptoException(String message) {
        super(message);
    }


    public CryptoException(Throwable e) {
        super(e);
    }

}
