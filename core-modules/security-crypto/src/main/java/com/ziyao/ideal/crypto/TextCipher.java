package com.ziyao.ideal.crypto;

import com.ziyao.ideal.core.codec.StringCodec;

/**
 * @author ziyao
 * 
 */
public interface TextCipher extends Algorithm {

    StringCodec getCodec();

    /**
     * 对给定的秘文进行解密并转换为字符串类型
     *
     * @return 返回解密后的数据
     */
    String decrypt(String encrypt);

    /**
     * 对原始明文进行加密
     *
     * @return 返回密文
     */
    String encrypt(String input);

}
