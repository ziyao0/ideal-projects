package com.ziyao.harbor.crypto.core;

import com.ziyao.crypto.PropertyResolver;
import com.ziyao.crypto.TextCipherProvider;

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
