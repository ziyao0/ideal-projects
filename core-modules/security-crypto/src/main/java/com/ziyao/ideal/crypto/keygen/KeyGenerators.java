package com.ziyao.ideal.crypto.keygen;

/**
 * @author ziyao zhang
 * 
 */
public abstract class KeyGenerators {

    private KeyGenerators() {
    }

    public static BytesKeyGenerator secureRandom() {
        return new SecureRandomBytesKeyGenerator();
    }

    public static BytesKeyGenerator secureRandom(int keyLength) {
        return new SecureRandomBytesKeyGenerator(keyLength);
    }

    public static BytesKeyGenerator shared(int keyLength) {
        return new SharedKeyGenerator(secureRandom(keyLength).generateKey());
    }

    public static StringKeyGenerator string() {
        return new HexEncodingStringKeyGenerator(secureRandom());
    }
}
