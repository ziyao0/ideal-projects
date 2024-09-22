package com.ziyao.ideal.encrypt.core;

import com.ziyao.ideal.crypto.PropertyResolver;
import com.ziyao.ideal.crypto.TextCipherProvider;

/**
 * @author ziyao zhang
 */
public interface CryptoContext {

    CodebookProperties getProperties();

    PropertyResolver getPropertyResolver();

    TextCipherProvider getTextCipherProvider();

    /**
     * 解密失败时是否失败
     *
     * @return {@code false} 打印异常日志
     */
    boolean isFailOnError();

}
