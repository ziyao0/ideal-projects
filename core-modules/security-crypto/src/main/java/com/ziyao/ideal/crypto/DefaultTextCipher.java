package com.ziyao.ideal.crypto;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.codec.StringCodec;

/**
 * @author ziyao zhang
 */
public class DefaultTextCipher implements TextCipher {

    private final BytesCipher bytesCipher;

    private final StringCodec stringCodec;

    public DefaultTextCipher(BytesCipher bytesCipher, StringCodec stringCodec) {
        this.bytesCipher = bytesCipher;
        this.stringCodec = stringCodec;
    }

    @Override
    public String getAlgorithm() {
        return bytesCipher.getAlgorithm();
    }

    @Override
    public StringCodec getCodec() {
        return stringCodec;
    }

    @Override
    public String decrypt(String encrypt) {
        byte[] decode = stringCodec.decode(encrypt);
        byte[] decrypt = bytesCipher.decrypt(decode);
        return Strings.toString(decrypt);
    }

    @Override
    public String encrypt(String input) {
        byte[] encrypt = bytesCipher.encrypt(Strings.toBytes(input));
        return stringCodec.encode(encrypt);
    }
}
