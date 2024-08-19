package com.ziyao.ideal.crypto.druid;

import com.ziyao.ideal.crypto.TextCipher;

import javax.security.auth.callback.NameCallback;


/**
 * @author ziyao zhang
 */
public class NameDecryptCallback extends NameCallback {
    
    private static final long serialVersionUID = 6060803312395586003L;

    private final TextCipher textCipher;


    public NameDecryptCallback(String prompt, TextCipher textCipher) {
        super(prompt);
        this.textCipher = textCipher;
    }

    @Override
    public String getName() {
        return textCipher.decrypt(super.getName());
    }
}
