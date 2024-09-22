package com.ziyao.ideal.encrypt.druid;

import com.alibaba.druid.util.DruidPasswordCallback;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.crypto.TextCipher;

import java.io.Serial;
import java.util.Properties;

/**
 * @author ziyao zhang
 */
public class PasswordDecryptCallback extends DruidPasswordCallback {
    @Serial
    private static final long serialVersionUID = -969291393141855980L;

    private static final String PASSWORD = "password";
    private final TextCipher textCipher;

    private String pd;

    public PasswordDecryptCallback(TextCipher textCipher) {
        this.textCipher = textCipher;
    }

    public PasswordDecryptCallback(String prompt, boolean echoOn, TextCipher textCipher) {
        super(prompt, echoOn);
        this.textCipher = textCipher;
    }

    public void setPd(String pd) {
        this.pd = pd;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String password = (String) properties.get("PASSWORD");
        if (Strings.hasText(password)) {
            this.pd = password;
        }
    }


    @Override
    public char[] getPassword() {
        return textCipher.decrypt(this.pd).toCharArray();
    }
}
