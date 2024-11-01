package com.ziyao.ideal.crypto.asymmetric;

import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.core.codec.Base64;
import com.ziyao.ideal.crypto.exception.CryptoException;
import com.ziyao.ideal.crypto.utils.KeyUtils;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 非对称基础，提供锁、私钥和公钥的持有
 *
 * @author ziyao
 * 
 */
public class BaseAsymmetric<T extends BaseAsymmetric<T>> implements Serializable {

    @Serial
    private static final long serialVersionUID = 3336907590201462580L;
    /**
     * 算法
     */
    protected String algorithm;

    @Getter
    protected PublicKey publicKey;
    @Getter
    protected PrivateKey privateKey;
    /**
     * 锁
     */
    protected final Lock lock = new ReentrantLock();

    // ------------------------------------------------------------------ Constructor start

    /**
     * 构造
     * <p>
     * 私钥和公钥同时为空时生成一对新的私钥和公钥<br>
     * 私钥和公钥可以单独传入一个，如此则只能使用此钥匙来做加密或者解密
     *
     * @param algorithm  算法
     * @param privateKey 私钥
     * @param publicKey  公钥
     */
    public BaseAsymmetric(String algorithm, PrivateKey privateKey, PublicKey publicKey) {
        init(algorithm, privateKey, publicKey);
    }
    // ------------------------------------------------------------------ Constructor end

    /**
     * 初始化<br>
     * 私钥和公钥同时为空时生成一对新的私钥和公钥<br>
     * 私钥和公钥可以单独传入一个，如此则只能使用此钥匙来做加密（签名）或者解密（校验）
     *
     * @param algorithm  算法
     * @param privateKey 私钥
     * @param publicKey  公钥
     */
    protected void init(String algorithm, PrivateKey privateKey, PublicKey publicKey) {
        this.algorithm = algorithm;

        if (null == privateKey && null == publicKey) {
            initKeys();
        } else {
            if (null != privateKey) {
                this.privateKey = privateKey;
            }
            if (null != publicKey) {
                this.publicKey = publicKey;
            }
        }
    }

    /**
     * 生成公钥和私钥
     */
    public void initKeys() {
        KeyPair keyPair = KeyUtils.generateKeyPair(this.algorithm);
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
    }

    // --------------------------------------------------------------------------------- Getters and Setters

    /**
     * 获得公钥
     *
     * @return 获得公钥
     */
    public String getPublicKeyBase64() {
        final PublicKey publicKey = getPublicKey();
        return (null == publicKey) ? null : Base64.encode(publicKey.getEncoded());
    }

    /**
     * 设置公钥
     *
     * @param publicKey 公钥
     * @return this
     */
    @SuppressWarnings("unchecked")
    public T setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
        return (T) this;
    }

    /**
     * 获得私钥
     *
     * @return 获得私钥
     */
    public String getPrivateKeyBase64() {
        final PrivateKey privateKey = getPrivateKey();
        return (null == privateKey) ? null : Base64.encode(privateKey.getEncoded());
    }

    /**
     * 设置私钥
     *
     * @param privateKey 私钥
     * @return this
     */
    @SuppressWarnings("unchecked")
    public T setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
        return (T) this;
    }

    /**
     * 设置密钥，可以是公钥{@link PublicKey}或者私钥{@link PrivateKey}
     *
     * @param key 密钥，可以是公钥{@link PublicKey}或者私钥{@link PrivateKey}
     * @return this
     */
    public T setKey(Key key) {
        Assert.notNull(key, "key must be not null !");

        if (key instanceof PublicKey) {
            return setPublicKey((PublicKey) key);
        } else if (key instanceof PrivateKey) {
            return setPrivateKey((PrivateKey) key);
        }
        throw new CryptoException("Unsupported key type: " + key.getClass());
    }

    /**
     * 根据密钥类型获得相应密钥
     *
     * @param type 类型 {@link }
     * @return {@link Key}
     */
    protected Key getKeyByType(KeyType type) {
        switch (type) {
            case PrivateKey:
                if (null == this.privateKey) {
                    throw new NullPointerException("Private key must not null when use it !");
                }
                return this.privateKey;
            case PublicKey:
                if (null == this.publicKey) {
                    throw new NullPointerException("Public key must not null when use it !");
                }
                return this.publicKey;
        }
        throw new CryptoException("Unsupported key type: " + type);
    }
}