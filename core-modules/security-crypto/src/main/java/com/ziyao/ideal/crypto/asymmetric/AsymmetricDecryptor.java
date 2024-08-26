package com.ziyao.ideal.crypto.asymmetric;

/**
 * 解密器  编解码器 非对称
 * Asymmetric
 *
 * @author ziyao zhang
 * 
 */
public interface AsymmetricDecryptor {

    /**
     * 对给定的密文信息进行解密
     *
     * @param bytes   密文信息
     * @param keyType 提供的秘钥信息
     * @return 返回解密后的信息
     */
    byte[] decrypt(byte[] bytes, KeyType keyType);
}
