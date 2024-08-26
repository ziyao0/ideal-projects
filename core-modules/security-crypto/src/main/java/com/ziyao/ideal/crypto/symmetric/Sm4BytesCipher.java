package com.ziyao.ideal.crypto.symmetric;

import com.ziyao.ideal.crypto.Algorithm;
import com.ziyao.ideal.crypto.BytesCipher;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ziyao zhang
 */
@Getter
public class Sm4BytesCipher implements BytesCipher, Serializable {

    @Serial
    private static final long serialVersionUID = -6862452587900440164L;
    private final String algorithm = Algorithm.SM4;


    private final SM4 sm4;

    public Sm4BytesCipher(Mode mode, Padding padding, byte[] key, byte[] iv) {
        this.sm4 = new SM4(mode, padding, key, iv);
    }

    public Sm4BytesCipher(SM4 sm4) {
        this.sm4 = sm4;
    }


    @Override
    public byte[] decrypt(byte[] encrypt) {
        return sm4.decrypt(encrypt);
    }

    @Override
    public byte[] encrypt(byte[] input) {
        return sm4.encrypt(input);
    }
}
