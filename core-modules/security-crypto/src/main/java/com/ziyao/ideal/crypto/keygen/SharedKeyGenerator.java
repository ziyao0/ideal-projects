package com.ziyao.ideal.crypto.keygen;

/**
 * @author ziyao zhang
 * 
 */
public class SharedKeyGenerator implements BytesKeyGenerator {

    private final byte[] sharedKey;

    SharedKeyGenerator(byte[] sharedKey) {
        this.sharedKey = sharedKey;
    }

    @Override
    public int getKeyLength() {
        return this.sharedKey.length;
    }

    @Override
    public byte[] generateKey() {
        return this.sharedKey;
    }

}
