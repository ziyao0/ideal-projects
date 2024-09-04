package com.ziyao.ideal.crypto.digest;

import com.google.common.base.Charsets;
import com.ziyao.ideal.core.ArrayUtils;
import com.ziyao.ideal.core.CharsetUtils;
import com.ziyao.ideal.core.HexUtils;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.crypto.AbstractAlgorithm;
import com.ziyao.ideal.crypto.asymmetric.DigestAlgorithm;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ziyao zhang
 * 
 */
public class Digester extends AbstractAlgorithm implements Serializable {

    
    private static final long serialVersionUID = -5710575887725018089L;
    /**
     * 代理的 JDK {@link MessageDigest} 实现.
     */
    private final MessageDigest digest;
    /**
     * 盐值
     */
    protected byte[] salt;
    /**
     * 加盐位置，即将盐值字符串放置在数据的index数，默认0
     */
    protected int saltPosition;
    /**
     * 散列次数
     */
    protected int digestCount;

    public Digester(String algorithm) {
        super(algorithm);
        try {
            this.digest = MessageDigest.getInstance(algorithm, new BouncyCastleProvider());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public Digester(DigestAlgorithm digestAlgorithm) {
        super(digestAlgorithm.getValue());
        try {
            this.digest = MessageDigest.getInstance(digestAlgorithm.getValue(), new BouncyCastleProvider());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成摘要，并转为16进制字符串<br>
     *
     * @param data 被摘要数据
     * @return 摘要
     */
    public String digestHex(byte[] data) {
        return HexUtils.encodeHexStr(digest(data));
    }

    /**
     * 生成文件摘要
     *
     * @param data 被摘要数据
     * @return 摘要
     */
    public String digestHex(String data) {
        return digestHex(data, Charsets.UTF_8);
    }

    /**
     * 生成文件摘要，并转为16进制字符串
     *
     * @param data        被摘要数据
     * @param charsetName 编码
     * @return 摘要
     */
    public String digestHex(String data, String charsetName) {
        return digestHex(data, CharsetUtils.forName(charsetName));
    }

    /**
     * 生成文件摘要，并转为16进制字符串
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return 摘要
     */
    public String digestHex(String data, Charset charset) {
        return HexUtils.encodeHexStr(digest(data, charset));
    }

    /**
     * 生成文件摘要
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return 摘要
     */
    public byte[] digest(String data, Charset charset) {
        return digest(Strings.toBytes(data, charset));
    }

    public byte[] digest() {
        return digest.digest();
    }

    public byte[] digest(byte[] input) {
        if (ArrayUtils.isEmpty(input)) {
            return new byte[0];
        } else {
            return digest.digest(input);
        }
    }
}