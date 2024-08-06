package com.ziyao.ideal.crypto;

import com.ziyao.ideal.core.Strings;

import java.util.List;

/**
 * @author ziyao zhang
 * @since 2023/10/25
 */
public record TextCipherProvider(List<TextCipher> textCiphers) {


    public TextCipher loadCipher(String algorithm) {
        if (Strings.hasText(algorithm)) {
            for (TextCipher textCipher : textCiphers()) {
                if (Strings.equalsIgnoreCase(algorithm, textCipher.getAlgorithm())) {
                    return textCipher;
                }
            }
        }
        return null;
    }

    @Override
    public List<TextCipher> textCiphers() {
        return textCiphers;
    }
}
