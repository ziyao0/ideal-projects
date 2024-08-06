package com.ziyao.ideal.config.crypto;

import com.ziyao.ideal.crypto.PropertyResolver;
import com.ziyao.ideal.crypto.TextCipher;
import com.ziyao.ideal.crypto.utils.SmUtils;

/**
 * @author ziyao
 */
public class DefaultDecryptCallback implements DecryptCallback {

    private final TextCipher textCipher;

    public DefaultDecryptCallback() {
        this.textCipher = createTextCipher();
    }

    private TextCipher createTextCipher() {
        String key = System.getProperty(ConstantPool.key);
        String iv = System.getProperty(ConstantPool.iv);
        return SmUtils.createSm4CBCTextCipherWithZeroPaddingAndHexCodec(key, iv);
    }

    @Override
    public String decrypt(String encrypt) throws Exception {
        encrypt = encrypt.substring(PropertyResolver.getPrefix(textCipher.getAlgorithm()).length());
        return textCipher.decrypt(encrypt);
    }

}
