package com.ziyao.crypto.keygen;

import com.ziyao.eis.core.HexUtils;

/**
 * @author ziyao zhang
 * @since 2024/3/26
 */
public class HexEncodingStringKeyGenerator implements StringKeyGenerator {

    private final BytesKeyGenerator keyGenerator;

    HexEncodingStringKeyGenerator(BytesKeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    @Override
    public String generateKey() {
        return new String(HexUtils.encodeHex(this.keyGenerator.generateKey()));
    }

}
