package com.ziyao.ideal.crypto;

import com.ziyao.ideal.core.Strings;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ziyao zhang
 */
@Getter
@Setter
public class Property {

    private String key;

    private Object value;

    private String algorithm;

    private TextCipher textCipher;

    public Property() {
    }

    public Property(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public boolean isEncryption() {
        return !Strings.isEmpty(textCipher);
    }

    @Override
    public String toString() {
        return "Property:key=" + key + ", value=" + value;
    }
}
