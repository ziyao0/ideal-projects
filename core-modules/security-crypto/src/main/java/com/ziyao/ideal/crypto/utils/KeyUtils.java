package com.ziyao.ideal.crypto.utils;

import com.ziyao.ideal.crypto.GlobalBouncyCastleProvider;
import com.ziyao.ideal.crypto.exception.CryptoException;
import com.ziyao.ideal.crypto.symmetric.SymmetricAlgorithm;
import com.ziyao.ideal.core.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.*;

/**
 * @author ziyao zhang
 * 
 */
public abstract class KeyUtils {

    /**
     * Java密钥库(Java Key Store，JKS)KEY_STORE
     */
    public static final String KEY_TYPE_JKS = "JKS";
    /**
     * jceks
     */
    public static final String KEY_TYPE_JCEKS = "jceks";
    /**
     * PKCS12是公钥加密标准，它规定了可包含所有私钥、公钥和证书。其以二进制格式存储，也称为 PFX 文件
     */
    public static final String KEY_TYPE_PKCS12 = "pkcs12";
    /**
     * Certification类型：X.509
     */
    public static final String CERT_TYPE_X509 = "X.509";

    /**
     * 默认密钥字节数
     *
     * <pre>
     * RSA/DSA
     * Default KeySize 1024
     * KeySize must be a multiple of 64, ranging from 512 to 1024 (inclusive).
     * </pre>
     */
    public static final int DEFAULT_KEY_SIZE = 1024;

    public static final String SM2_DEFAULT_CURVE = SmUtils.SM2_CURVE_NAME;

    /**
     * 生成用于非对称加密的公钥和私钥，仅用于非对称加密<br>
     * 密钥对生成算法见：<a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyPairGenerator">...</a>
     *
     * @param algorithm 非对称加密算法
     * @return {@link KeyPair}
     */
    public static KeyPair generateKeyPair(String algorithm) {
        int keySize = DEFAULT_KEY_SIZE;
        if ("ECIES".equalsIgnoreCase(algorithm)) {
            // ECIES算法对KEY的长度有要求，此处默认256
            keySize = 256;
        }

        return generateKeyPair(algorithm, keySize);
    }

    /**
     * 生成用于非对称加密的公钥和私钥<br>
     * 密钥对生成算法见：<a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyPairGenerator">...</a>
     *
     * @param algorithm 非对称加密算法
     * @param keySize   密钥模（modulus ）长度
     * @return {@link KeyPair}
     */
    public static KeyPair generateKeyPair(String algorithm, int keySize) {
        return generateKeyPair(algorithm, keySize, null);
    }

    /**
     * 生成用于非对称加密的公钥和私钥<br>
     * 密钥对生成算法见：<a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyPairGenerator">...</a>
     *
     * @param algorithm 非对称加密算法
     * @param keySize   密钥模（modulus ）长度
     * @param seed      种子
     * @return {@link KeyPair}
     */
    public static KeyPair generateKeyPair(String algorithm, int keySize, byte[] seed) {
        // SM2算法需要单独定义其曲线生成
        if ("SM2".equalsIgnoreCase(algorithm)) {
            final ECGenParameterSpec sm2p256v1 = new ECGenParameterSpec(SM2_DEFAULT_CURVE);
            return generateKeyPair(algorithm, keySize, seed, sm2p256v1);
        }

        return generateKeyPair(algorithm, keySize, seed, (AlgorithmParameterSpec[]) null);
    }

    /**
     * 生成用于非对称加密的公钥和私钥<br>
     * 密钥对生成算法见：<a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyPairGenerator">...</a>
     *
     * <p>
     * 对于非对称加密算法，密钥长度有严格限制，具体如下：
     *
     * <p>
     * <b>RSA：</b>
     * <pre>
     * RS256、PS256：2048 bits
     * RS384、PS384：3072 bits
     * RS512、RS512：4096 bits
     * </pre>
     *
     * <p>
     * <b>EC（Elliptic Curve）：</b>
     * <pre>
     * EC256：256 bits
     * EC384：384 bits
     * EC512：512 bits
     * </pre>
     *
     * @param algorithm 非对称加密算法
     * @param keySize   密钥模（modulus ）长度（单位bit）
     * @param seed      种子
     * @param params    {@link AlgorithmParameterSpec}
     * @return {@link KeyPair}
     */
    public static KeyPair generateKeyPair(String algorithm, int keySize, byte[] seed, AlgorithmParameterSpec... params) {
        return generateKeyPair(algorithm, keySize, Randoms.createSecureRandom(seed), params);
    }

    /**
     * 生成用于非对称加密的公钥和私钥<br>
     * 密钥对生成算法见：<a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyPairGenerator">...</a>
     *
     * <p>
     * 对于非对称加密算法，密钥长度有严格限制，具体如下：
     *
     * <p>
     * <b>RSA：</b>
     * <pre>
     * RS256、PS256：2048 bits
     * RS384、PS384：3072 bits
     * RS512、RS512：4096 bits
     * </pre>
     *
     * <p>
     * <b>EC（Elliptic Curve）：</b>
     * <pre>
     * EC256：256 bits
     * EC384：384 bits
     * EC512：512 bits
     * </pre>
     *
     * @param algorithm 非对称加密算法
     * @param keySize   密钥模（modulus ）长度（单位bit）
     * @param random    {@link SecureRandom} 对象，创建时可选传入seed
     * @param params    {@link AlgorithmParameterSpec}
     * @return {@link KeyPair}
     */
    public static KeyPair generateKeyPair(String algorithm, int keySize, SecureRandom random, AlgorithmParameterSpec... params) {
        algorithm = getAlgorithmAfterWith(algorithm);
        final KeyPairGenerator keyPairGen = getKeyPairGenerator(algorithm);

        // 密钥模（modulus ）长度初始化定义
        if (keySize > 0) {
            // key长度适配修正
            if ("EC".equalsIgnoreCase(algorithm) && keySize > 256) {
                // 对于EC（EllipticCurve）算法，密钥长度有限制，在此使用默认256
                keySize = 256;
            }
            if (null != random) {
                keyPairGen.initialize(keySize, random);
            } else {
                keyPairGen.initialize(keySize);
            }
        }

        // 自定义初始化参数
        if (!ArrayUtils.isEmpty(params)) {
            for (AlgorithmParameterSpec param : params) {
                if (null == param) {
                    continue;
                }
                try {
                    if (null != random) {
                        keyPairGen.initialize(param, random);
                    } else {
                        keyPairGen.initialize(param);
                    }
                } catch (InvalidAlgorithmParameterException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return keyPairGen.generateKeyPair();
    }

    /**
     * 获取用于密钥生成的算法<br>
     * 获取XXXwithXXX算法的后半部分算法，如果为ECDSA或SM2，返回算法为EC
     *
     * @param algorithm XXXwithXXX算法
     * @return 算法
     */
    public static String getAlgorithmAfterWith(String algorithm) {
        Assert.notNull(algorithm, "algorithm must be not null !");

        if (Strings.startsWith(algorithm, "ECIESWith")) {
            return "EC";
        }
        if (algorithm.toUpperCase().contains("ECDSA")
                || algorithm.toUpperCase().contains("SM2")
                || algorithm.toUpperCase().contains("ECIES")
        ) {
            algorithm = "EC";
        }
        return algorithm;
    }

    /**
     * 获取{@link KeyPairGenerator}
     *
     * @param algorithm 非对称加密算法
     * @return {@link KeyPairGenerator}
     */
    public static KeyPairGenerator getKeyPairGenerator(String algorithm) {
        final Provider provider = GlobalBouncyCastleProvider.INSTANCE.getProvider();

        KeyPairGenerator keyPairGen;
        try {
            keyPairGen = (null == provider) //
                    ? KeyPairGenerator.getInstance(getMainAlgorithm(algorithm)) //
                    : KeyPairGenerator.getInstance(getMainAlgorithm(algorithm), provider);//
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return keyPairGen;
    }

    /**
     * 获取主体算法名，例如RSA/ECB/PKCS1Padding的主体算法是RSA
     *
     * @param algorithm XXXwithXXX算法
     * @return 主体算法名
     */
    public static String getMainAlgorithm(String algorithm) {
        Assert.notNull(algorithm, "Algorithm must be not blank!");
        final int slashIndex = algorithm.indexOf(CharUtils.SLASH);
        if (slashIndex > 0) {
            return algorithm.substring(0, slashIndex);
        }
        return algorithm;
    }

    /**
     * 生成私钥，仅用于非对称加密<br>
     * 采用PKCS#8规范，此规范定义了私钥信息语法和加密私钥语法<br>
     * 算法见：<a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyFactory">...</a>
     *
     * @param algorithm 算法，如RSA、EC、SM2等
     * @param key       密钥，PKCS#8格式
     * @return 私钥 {@link PrivateKey}
     */
    public static PrivateKey generatePrivateKey(String algorithm, byte[] key) {
        if (null == key) {
            return null;
        }
        return generatePrivateKey(algorithm, new PKCS8EncodedKeySpec(key));
    }

    /**
     * 生成私钥，仅用于非对称加密<br>
     * 算法见：<a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyFactory">...</a>
     *
     * @param algorithm 算法，如RSA、EC、SM2等
     * @param keySpec   {@link KeySpec}
     * @return 私钥 {@link PrivateKey}
     */
    public static PrivateKey generatePrivateKey(String algorithm, KeySpec keySpec) {
        if (null == keySpec) {
            return null;
        }
        algorithm = getAlgorithmAfterWith(algorithm);
        try {
            return getKeyFactory(algorithm).generatePrivate(keySpec);
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 获取{@link KeyFactory}
     *
     * @param algorithm 非对称加密算法
     * @return {@link KeyFactory}
     */
    public static KeyFactory getKeyFactory(String algorithm) {
        final Provider provider = GlobalBouncyCastleProvider.INSTANCE.getProvider();

        KeyFactory keyFactory;
        try {
            keyFactory = (null == provider) //
                    ? KeyFactory.getInstance(getMainAlgorithm(algorithm)) //
                    : KeyFactory.getInstance(getMainAlgorithm(algorithm), provider);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
        return keyFactory;
    }

    /**
     * 生成公钥，仅用于非对称加密<br>
     * 采用X509证书规范<br>
     * 算法见：<a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyFactory">...</a>
     *
     * @param algorithm 算法
     * @param key       密钥，必须为DER编码存储
     * @return 公钥 {@link PublicKey}
     */
    public static PublicKey generatePublicKey(String algorithm, byte[] key) {
        if (null == key) {
            return null;
        }
        return generatePublicKey(algorithm, new X509EncodedKeySpec(key));
    }

    /**
     * 生成公钥，仅用于非对称加密<br>
     * 算法见：<a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyFactory">...</a>
     *
     * @param algorithm 算法
     * @param keySpec   {@link KeySpec}
     * @return 公钥 {@link PublicKey}
     */
    public static PublicKey generatePublicKey(String algorithm, KeySpec keySpec) {
        if (null == keySpec) {
            return null;
        }
        algorithm = getAlgorithmAfterWith(algorithm);
        try {
            return getKeyFactory(algorithm).generatePublic(keySpec);
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 生成 {@link SecretKey}，仅用于对称加密和摘要算法密钥生成
     *
     * @param algorithm 算法
     * @param key       密钥，如果为{@code null} 自动生成随机密钥
     * @return {@link SecretKey}
     */
    public static SecretKey generateKey(String algorithm, byte[] key) {
        Assert.notNull(algorithm, "Algorithm is blank!");
        SecretKey secretKey;
        if (algorithm.startsWith("PBE")) {
            // PBE密钥
            secretKey = generatePBEKey(algorithm, (null == key) ? null : Strings.toString(key, CharsetUtils.CHARSET_UTF_8).toCharArray());
        } else if (algorithm.startsWith("DES")) {
            // DES密钥
            secretKey = generateDESKey(algorithm, key);
        } else {
            // 其它算法密钥
            secretKey = (null == key) ? generateKey(algorithm) : new SecretKeySpec(key, algorithm);
        }
        return secretKey;
    }

    /**
     * 生成 {@link SecretKey}，仅用于对称加密和摘要算法密钥生成
     *
     * @param algorithm 算法，支持PBE算法
     * @return {@link SecretKey}
     */
    public static SecretKey generateKey(String algorithm) {
        return generateKey(algorithm, -1);
    }

    /**
     * 生成 {@link SecretKey}，仅用于对称加密和摘要算法密钥生成<br>
     * 当指定keySize&lt;0时，AES默认长度为128，其它算法不指定。
     *
     * @param algorithm 算法，支持PBE算法
     * @param keySize   密钥长度，&lt;0表示不设定密钥长度，即使用默认长度
     * @return {@link SecretKey}
     */
    public static SecretKey generateKey(String algorithm, int keySize) {
        return generateKey(algorithm, keySize, null);
    }

    /**
     * 生成 {@link SecretKey}，仅用于对称加密和摘要算法密钥生成<br>
     * 当指定keySize&lt;0时，AES默认长度为128，其它算法不指定。
     *
     * @param algorithm 算法，支持PBE算法
     * @param keySize   密钥长度，&lt;0表示不设定密钥长度，即使用默认长度
     * @param random    随机数生成器，null表示默认
     * @return {@link SecretKey}
     */
    public static SecretKey generateKey(String algorithm, int keySize, SecureRandom random) {
        algorithm = getMainAlgorithm(algorithm);

        final KeyGenerator keyGenerator = getKeyGenerator(algorithm);
        if (keySize <= 0 && SymmetricAlgorithm.AES.getValue().equals(algorithm)) {
            // 对于AES的密钥，除非指定，否则强制使用128位
            keySize = 128;
        }

        if (keySize > 0) {
            if (null == random) {
                keyGenerator.init(keySize);
            } else {
                keyGenerator.init(keySize, random);
            }
        }
        return keyGenerator.generateKey();
    }

    /**
     * 获取{@link KeyGenerator}
     *
     * @param algorithm 对称加密算法
     * @return {@link KeyGenerator}
     */
    public static KeyGenerator getKeyGenerator(String algorithm) {
        final Provider provider = GlobalBouncyCastleProvider.INSTANCE.getProvider();

        KeyGenerator generator;
        try {
            generator = (null == provider) //
                    ? KeyGenerator.getInstance(getMainAlgorithm(algorithm)) //
                    : KeyGenerator.getInstance(getMainAlgorithm(algorithm), provider);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
        return generator;
    }

    /**
     * 生成 {@link SecretKey}
     *
     * @param algorithm DES算法，包括DES、DESede等
     * @param key       密钥
     * @return {@link SecretKey}
     */
    public static SecretKey generateDESKey(String algorithm, byte[] key) {
        if (Strings.isEmpty(algorithm) || !algorithm.startsWith("DES")) {
            throw new CryptoException("Algorithm [" + algorithm + "] is not a DES algorithm!");
        }

        SecretKey secretKey;
        if (null == key) {
            secretKey = generateKey(algorithm);
        } else {
            KeySpec keySpec;
            try {
                if (algorithm.startsWith("DESede")) {
                    // DESede兼容
                    keySpec = new DESedeKeySpec(key);
                } else {
                    keySpec = new DESKeySpec(key);
                }
            } catch (InvalidKeyException e) {
                throw new CryptoException(e);
            }
            secretKey = generateKey(algorithm, keySpec);
        }
        return secretKey;
    }

    /**
     * 生成PBE {@link SecretKey}
     *
     * @param algorithm PBE算法，包括：PBEWithMD5AndDES、PBEWithSHA1AndDESede、PBEWithSHA1AndRC2_40等
     * @param key       密钥
     * @return {@link SecretKey}
     */
    public static SecretKey generatePBEKey(String algorithm, char[] key) {
        if (Strings.isEmpty(algorithm) || !algorithm.startsWith("PBE")) {
            throw new CryptoException("Algorithm [" + algorithm + "] is not a PBE algorithm!");
        }

        if (null == key) {
            key = Randoms.randomString(32).toCharArray();
        }
        PBEKeySpec keySpec = new PBEKeySpec(key);
        return generateKey(algorithm, keySpec);
    }

    /**
     * 生成 {@link SecretKey}，仅用于对称加密和摘要算法
     *
     * @param algorithm 算法
     * @param keySpec   {@link KeySpec}
     * @return {@link SecretKey}
     */
    public static SecretKey generateKey(String algorithm, KeySpec keySpec) {
        final SecretKeyFactory keyFactory = getSecretKeyFactory(algorithm);
        try {
            return keyFactory.generateSecret(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 获取{@link SecretKeyFactory}
     *
     * @param algorithm 对称加密算法
     * @return {@link KeyFactory}
     */
    public static SecretKeyFactory getSecretKeyFactory(String algorithm) {
        final Provider provider = GlobalBouncyCastleProvider.INSTANCE.getProvider();

        SecretKeyFactory keyFactory;
        try {
            keyFactory = (null == provider) //
                    ? SecretKeyFactory.getInstance(getMainAlgorithm(algorithm)) //
                    : SecretKeyFactory.getInstance(getMainAlgorithm(algorithm), provider);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
        return keyFactory;
    }
}
