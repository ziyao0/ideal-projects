package com.ziyao.config.crypto;

import com.ziyao.crypto.PropertyResolver;
import com.ziyao.crypto.TextCipher;
import com.ziyao.crypto.utils.SmUtils;

/**
 * @author ziyao
 */
public class DefaultEncryptCallback implements EncryptCallback {


    private final TextCipher textCipher;

    public DefaultEncryptCallback() {
        this.textCipher = createTextCipher();
    }

    private TextCipher createTextCipher() {
        String key = System.getProperty(ConstantPool.key);
        String iv = System.getProperty(ConstantPool.iv);
        return SmUtils.createSm4CBCTextCipherWithZeroPaddingAndHexCodec(key, iv);
    }

    @Override
    public String encrypt(String text) throws Exception {
        return PropertyResolver.getPrefix(textCipher.getAlgorithm()) + textCipher.encrypt(text);
    }
}
