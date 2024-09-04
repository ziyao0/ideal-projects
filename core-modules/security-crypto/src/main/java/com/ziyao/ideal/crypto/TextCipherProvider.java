package com.ziyao.ideal.crypto;

import com.ziyao.ideal.core.Strings;
import lombok.Getter;

import java.util.List;

/**
 * @author ziyao zhang
 */
@Getter
public class TextCipherProvider {

    private final List<TextCipher> textCiphers;

    public TextCipherProvider(List<TextCipher> textCiphers) {
        this.textCiphers = textCiphers;
    }

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

    public List<TextCipher> textCiphers() {
        return textCiphers;
    }
}
