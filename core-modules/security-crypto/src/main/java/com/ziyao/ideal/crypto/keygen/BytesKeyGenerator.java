package com.ziyao.ideal.crypto.keygen;

/**
 * @author ziyao zhang
 * @since 2024/3/26
 */
public interface BytesKeyGenerator {

    int getKeyLength();

    byte[] generateKey();
}
