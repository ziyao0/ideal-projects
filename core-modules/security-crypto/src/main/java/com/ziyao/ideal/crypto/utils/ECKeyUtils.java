package com.ziyao.ideal.crypto.utils;

import com.ziyao.ideal.crypto.exception.CryptoException;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.spec.OpenSSHPrivateKeySpec;
import org.bouncycastle.jcajce.spec.OpenSSHPublicKeySpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.util.BigIntegers;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;

/**
 * @author ziyao zhang
 * 
 */
public abstract class ECKeyUtils {

    /**
     * 转换为 ECPrivateKeyParameters
     *
     * @param d 私钥d值
     * @return ECPrivateKeyParameters
     */
    public static ECPrivateKeyParameters toSm2PrivateParams(byte[] d) {
        return toPrivateParams(d, SmUtils.SM2_DOMAIN_PARAMS);
    }

    /**
     * 转换为 ECPrivateKeyParameters
     *
     * @param d                私钥d值
     * @param domainParameters ECDomainParameters
     * @return ECPrivateKeyParameters
     */
    public static ECPrivateKeyParameters toPrivateParams(byte[] d, ECDomainParameters domainParameters) {
        return toPrivateParams(BigIntegers.fromUnsignedByteArray(d), domainParameters);
    }

    /**
     * 转换为 ECPrivateKeyParameters
     *
     * @param d                私钥d值
     * @param domainParameters ECDomainParameters
     * @return ECPrivateKeyParameters
     */
    public static ECPrivateKeyParameters toPrivateParams(BigInteger d, ECDomainParameters domainParameters) {
        if (null == d) {
            return null;
        }
        return new ECPrivateKeyParameters(d, domainParameters);
    }

    /**
     * 转换为SM2的ECPublicKeyParameters
     *
     * @param xBytes 公钥X
     * @param yBytes 公钥Y
     * @return ECPublicKeyParameters
     */
    public static ECPublicKeyParameters toSm2PublicParams(byte[] xBytes, byte[] yBytes) {
        return toPublicParams(xBytes, yBytes, SmUtils.SM2_DOMAIN_PARAMS);
    }

    /**
     * 转换为ECPublicKeyParameters
     *
     * @param xBytes           公钥X
     * @param yBytes           公钥Y
     * @param domainParameters ECDomainParameters曲线参数
     * @return ECPublicKeyParameters
     */
    public static ECPublicKeyParameters toPublicParams(byte[] xBytes, byte[] yBytes, ECDomainParameters domainParameters) {
        if (null == xBytes || null == yBytes) {
            return null;
        }
        return toPublicParams(BigIntegers.fromUnsignedByteArray(xBytes), BigIntegers.fromUnsignedByteArray(yBytes), domainParameters);
    }

    /**
     * 转换为ECPublicKeyParameters
     *
     * @param x                公钥X
     * @param y                公钥Y
     * @param domainParameters ECDomainParameters
     * @return ECPublicKeyParameters
     */
    public static ECPublicKeyParameters toPublicParams(BigInteger x, BigInteger y, ECDomainParameters domainParameters) {
        if (null == x || null == y) {
            return null;
        }
        final ECCurve curve = domainParameters.getCurve();
        return toPublicParams(curve.createPoint(x, y), domainParameters);
    }

    /**
     * 转换为ECPublicKeyParameters
     *
     * @param point            曲线坐标点
     * @param domainParameters ECDomainParameters
     * @return ECPublicKeyParameters
     */
    public static ECPublicKeyParameters toPublicParams(org.bouncycastle.math.ec.ECPoint point, ECDomainParameters domainParameters) {
        return new ECPublicKeyParameters(point, domainParameters);
    }

    /**
     * 尝试解析转换各种类型私钥为{@link ECPrivateKeyParameters}，支持包括：
     *
     * <ul>
     *     <li>D值</li>
     *     <li>PKCS#8</li>
     *     <li>PKCS#1</li>
     * </ul>
     *
     * @param privateKeyBytes 私钥
     * @return {@link ECPrivateKeyParameters}
     */
    public static ECPrivateKeyParameters decodePrivateKeyParams(byte[] privateKeyBytes) {
        try {
            // 尝试D值
            return toSm2PrivateParams(privateKeyBytes);
        } catch (Exception ignore) {
            // ignore
        }

        PrivateKey privateKey;
        //尝试PKCS#8
        try {
            privateKey = KeyUtils.generatePrivateKey("sm2", privateKeyBytes);
        } catch (Exception ignore) {
            // 尝试PKCS#1
            privateKey = KeyUtils.generatePrivateKey("sm2", createOpenSSHPrivateKeySpec(privateKeyBytes));
        }

        return toPrivateParams(privateKey);
    }

    /**
     * 创建{@link OpenSSHPrivateKeySpec}
     *
     * @param key 私钥，需为PKCS#1格式
     * @return {@link OpenSSHPrivateKeySpec}
     */
    public static KeySpec createOpenSSHPrivateKeySpec(byte[] key) {
        return new OpenSSHPrivateKeySpec(key);
    }

    /**
     * 私钥转换为 {@link ECPrivateKeyParameters}
     *
     * @param privateKey 私钥，传入null返回null
     * @return {@link ECPrivateKeyParameters}或null
     */
    public static ECPrivateKeyParameters toPrivateParams(PrivateKey privateKey) {
        if (null == privateKey) {
            return null;
        }
        try {
            return (ECPrivateKeyParameters) ECUtil.generatePrivateKeyParameter(privateKey);
        } catch (InvalidKeyException e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 尝试解析转换各种类型公钥为{@link ECPublicKeyParameters}，支持包括：
     *
     * <ul>
     *     <li>Q值</li>
     *     <li>X.509</li>
     *     <li>PKCS#1</li>
     * </ul>
     *
     * @param publicKeyBytes 公钥
     * @return {@link ECPublicKeyParameters}
     */
    public static ECPublicKeyParameters decodePublicKeyParams(byte[] publicKeyBytes) {
        try {
            // 尝试Q值
            return toSm2PublicParams(publicKeyBytes);
        } catch (Exception ignore) {
            // ignore
        }

        PublicKey publicKey;
        //尝试X.509
        try {
            publicKey = KeyUtils.generatePublicKey("sm2", publicKeyBytes);
        } catch (Exception ignore) {
            // 尝试PKCS#1
            publicKey = KeyUtils.generatePublicKey("sm2", createOpenSSHPublicKeySpec(publicKeyBytes));
        }

        return toPublicParams(publicKey);
    }

    /**
     * 创建{@link OpenSSHPublicKeySpec}
     *
     * @param key 公钥，需为PKCS#1格式
     * @return {@link OpenSSHPublicKeySpec}
     */
    public static KeySpec createOpenSSHPublicKeySpec(byte[] key) {
        return new OpenSSHPublicKeySpec(key);
    }


    /**
     * 转换为 ECPublicKeyParameters
     *
     * @param q 公钥Q值
     * @return ECPublicKeyParameters
     */
    public static ECPublicKeyParameters toSm2PublicParams(byte[] q) {
        return toPublicParams(q, SmUtils.SM2_DOMAIN_PARAMS);
    }

    /**
     * 转换为ECPublicKeyParameters
     *
     * @param pointEncoded     被编码的曲线坐标点
     * @param domainParameters ECDomainParameters
     * @return ECPublicKeyParameters
     */
    public static ECPublicKeyParameters toPublicParams(byte[] pointEncoded, ECDomainParameters domainParameters) {
        final ECCurve curve = domainParameters.getCurve();
        return toPublicParams(curve.decodePoint(pointEncoded), domainParameters);
    }

    /**
     * 公钥转换为 {@link ECPublicKeyParameters}
     *
     * @param publicKey 公钥，传入null返回null
     * @return {@link ECPublicKeyParameters}或null
     */
    public static ECPublicKeyParameters toPublicParams(PublicKey publicKey) {
        if (null == publicKey) {
            return null;
        }
        try {
            return (ECPublicKeyParameters) ECUtil.generatePublicKeyParameter(publicKey);
        } catch (InvalidKeyException e) {
            throw new CryptoException(e);
        }
    }
}
