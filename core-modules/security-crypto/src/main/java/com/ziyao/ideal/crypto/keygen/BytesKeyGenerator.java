package com.ziyao.ideal.crypto.keygen;

/**
 * @author ziyao zhang
 * 
 */
public interface BytesKeyGenerator {

    int getKeyLength();

    byte[] generateKey();
}
