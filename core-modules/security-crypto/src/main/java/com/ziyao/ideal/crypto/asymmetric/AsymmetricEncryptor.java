package com.ziyao.ideal.crypto.asymmetric;


/**
 * @author ziyao
 * 
 */
public interface AsymmetricEncryptor {

    /**
     * 加密
     *
     * @param data    被加密的bytes
     * @param keyType 私钥或公钥 {@link KeyType}
     * @return 加密后的bytes
     */
    byte[] encrypt(byte[] data, KeyType keyType);
}
