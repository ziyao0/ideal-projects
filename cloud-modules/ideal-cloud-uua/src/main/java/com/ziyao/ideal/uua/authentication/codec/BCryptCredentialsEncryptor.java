package com.ziyao.ideal.uua.authentication.codec;

import com.ziyao.crypto.digest.BCrypt;
import com.ziyao.crypto.digest.BCryptVersion;
import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.core.Randoms;
import com.ziyao.ideal.core.RegexPool;
import com.ziyao.ideal.core.Strings;

import java.security.SecureRandom;
import java.util.regex.Pattern;

/**
 * @author ziyao zhang
 */
public final class BCryptCredentialsEncryptor implements CredentialsEncryptor {

    /**
     * bcrypt 正则表达式
     */
    private final Pattern BCRYPT_PATTERN = RegexPool.BCRYPT_PATTERN.forName();
    private final int strength;
    private final BCryptVersion version;
    private final SecureRandom random;

    public BCryptCredentialsEncryptor() {
        this.version = BCryptVersion.$2B;
        this.strength = 8;
        this.random = Randoms.getSecureRandom();
    }

    @Override
    public String encrypt(CharSequence plaintext) {
        Assert.notNull(plaintext, "密码不能为空！");
        String salt = getSalt();
        return BCrypt.hashpw(plaintext.toString(), salt);
    }

    @Override
    public boolean matches(CharSequence plaintext, String ciphertext) {
        Assert.isTrue(Strings.hasText(plaintext), "明文不能为空！");
        Assert.isTrue(Strings.hasText(ciphertext), "密文不能为空！");
        Assert.isTrue(this.BCRYPT_PATTERN.matcher(ciphertext).matches(), "Encoded credentials does not look like BCrypt");
        return BCrypt.checkpw(plaintext.toString(), ciphertext);
    }

    private String getSalt() {
        if (this.random != null) {
            return BCrypt.gensalt(this.version.getVersion(), this.strength, this.random);
        }
        return BCrypt.gensalt(this.version.getVersion(), this.strength);
    }

    public static void main(String[] args) {
        BCryptCredentialsEncryptor bCryptCredentialsEncryptor = new BCryptCredentialsEncryptor();
        String encrypt = bCryptCredentialsEncryptor.encrypt("1qaz@WSX");
        System.out.println(encrypt);


        System.out.println(bCryptCredentialsEncryptor.BCRYPT_PATTERN.matcher(encrypt).matches());

        System.out.println(bCryptCredentialsEncryptor.matches("1qaz@WSX", encrypt));

    }
}
