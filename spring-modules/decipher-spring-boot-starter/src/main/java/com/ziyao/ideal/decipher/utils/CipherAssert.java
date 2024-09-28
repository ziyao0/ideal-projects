package com.ziyao.ideal.decipher.utils;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.crypto.Codebook;
import com.ziyao.ideal.decipher.core.CodebookProperties;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public abstract class CipherAssert {

    public static void assertLegal(CodebookProperties local, CodebookProperties external) {
        // @formatter:off
        if (isOneEmpty(local, external)) {
            newRuntimeException("配置不能仅储存在本地或者外部");
        }
        else if (isAllNotEmpty(local, external)) {
            assertSM2Legal(local.getSm2(), external.getSm2());
            assertSM4Legal(local.getSm4(), external.getSm4());
        }
        // @formatter:on
    }

    public static void assertSM4Legal(Codebook.KeyIv local, Codebook.KeyIv external) {
        // @formatter:off
        if (isOneEmpty(local, external)) {
            newRuntimeException("SM4配置不能仅储存在本地或者外部");
        }
        else if (isAllNotEmpty(local, external)) {
            assertSM4(local);
            assertSM4(external);

            String localKey = local.getKey();
            String localIv = local.getIv();
            String externalKey = external.getKey();
            String externalIv = external.getIv();

            if (isAllEmpty(localKey, externalKey)) {
                newRuntimeException("SM4密钥KEY信息不能为空");
            }
            if (isAllEmpty(localIv, externalIv)) {
                newRuntimeException("SM4向量IV信息不能为空");
            }
            if (!isOneEmpty(localKey, externalKey)) {
                newRuntimeException("SM4密钥KEY信息不能同时在多个配置文件中");
            }
            if (!isOneEmpty(localIv, externalIv)) {
                newRuntimeException("SM4向量IV不能同时在多个配置文件中");
            }
        }
        // @formatter:on
    }

    public static void assertSM2Legal(Codebook.KeyPair local, Codebook.KeyPair external) {
        // @formatter:off
        if (isOneEmpty(local, external)) {
            newRuntimeException("SM2配置不能仅储存在本地或者外部");
        }
        else if (isAllNotEmpty(local, external)) {
            assertSM2(local);
            assertSM2(external);
            String localSm2PrivateKey = local.getPrivateKey();
            String externalSm2PrivateKey = local.getPrivateKey();
            String localSm2PublicKey = external.getPublicKey();
            String externalSm2PublicKey = external.getPublicKey();

            if (isAllEmpty(localSm2PrivateKey, externalSm2PrivateKey)) {
                newRuntimeException("SM2私钥信息不能为空");
            }
            if (isAllEmpty(localSm2PublicKey, externalSm2PublicKey)) {
                newRuntimeException("SM2公钥信息不能为空");
            }
            if (!isOneEmpty(localSm2PublicKey, externalSm2PublicKey)) {
                newRuntimeException("SM2公钥信息不能同时存在多个配置文件中");
            }
            if (!isOneEmpty(localSm2PrivateKey, externalSm2PrivateKey)) {
                newRuntimeException("SM2私钥信息不能同时存在多个配置文件中");
            }
        }
        // @formatter:on
    }


    private static void assertSM4(Codebook.KeyIv keyIv) {
        if (keyIv != null) {
            String key = keyIv.getKey();
            String iv = keyIv.getIv();

            if (hasText(key) && hasText(iv)) {
                newRuntimeException("SM4密钥KEY和向量IV不能配置在同一配置中!");
            }
        } else {
            newRuntimeException("SM4配置信息不能为空");
        }
    }

    private static void assertSM2(Codebook.KeyPair keyPair) {
        if (keyPair != null) {
            String publicKey = keyPair.getPublicKey();
            String privateKey = keyPair.getPrivateKey();

            if (hasText(publicKey) && hasText(privateKey)) {
                newRuntimeException("SM2公私钥不能配置在同一配置中!");
            }
        } else {
            newRuntimeException("SM2配置信息不能为空");
        }
    }


    private static boolean hasText(String val) {
        return Strings.hasText(val);
    }

    private static boolean isAllEmpty(String val1, String val2) {
        return !Strings.hasText(val1) && !Strings.hasText(val2);
    }

    private static boolean isOneEmpty(Object o1, Object o2) {

        if (o1 instanceof String && o2 instanceof String) {
            boolean b1 = Strings.hasText(o1.toString());
            boolean b2 = Strings.hasText(o2.toString());

            return (b1 & !b2) || (!b1 && b2);
        } else {
            return (o1 == null && o2 != null) || (o1 != null && o2 == null);
        }
    }

    private static boolean isAllNotEmpty(Object o1, Object o2) {
        return o1 != null && o2 != null;
    }

    private CipherAssert() {

    }

    private static void newRuntimeException(String message) {
        throw new RuntimeException(message);
    }
}
