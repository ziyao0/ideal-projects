package com.ziyao.ideal.crypto.digest;

import com.google.common.base.Charsets;
import com.ziyao.ideal.core.CharsetUtils;
import com.ziyao.ideal.crypto.asymmetric.DigestAlgorithm;

import java.nio.charset.Charset;

/**
 * @author ziyao
 * 
 */
public abstract class DigestUtils {

    private DigestUtils() {
    }

    /**
     * 计算32位MD5摘要值
     *
     * @param data 被摘要数据
     * @return MD5摘要
     */
    public static byte[] md5(byte[] data) {
        return new MD5().digest(data);
    }

    /**
     * 计算32位MD5摘要值
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return MD5摘要
     */
    public static byte[] md5(String data, String charset) {
        return new MD5().digest(data, CharsetUtils.forName(charset));
    }

    /**
     * 计算32位MD5摘要值，使用UTF-8编码
     *
     * @param data 被摘要数据
     * @return MD5摘要
     */
    public static byte[] md5(String data) {
        return md5(data, CharsetUtils.UTF_8);
    }

    /**
     * 计算32位MD5摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex(byte[] data) {
        return new MD5().digestHex(data);
    }

    /**
     * 计算32位MD5摘要值，并转为16进制字符串
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex(String data, String charset) {
        return new MD5().digestHex(data, charset);
    }

    /**
     * 计算32位MD5摘要值，并转为16进制字符串
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex(String data, Charset charset) {
        return new MD5().digestHex(data, charset);
    }

    /**
     * 计算32位MD5摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex(String data) {
        return md5Hex(data, Charsets.UTF_8);
    }

    /**
     * 计算16位MD5摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex16(byte[] data) {
        return new MD5().digestHex16(data);
    }

    /**
     * 计算16位MD5摘要值，并转为16进制字符串
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex16(String data, Charset charset) {
        return new MD5().digestHex16(data, charset);
    }

    /**
     * 计算16位MD5摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex16(String data) {
        return md5Hex16(data, CharsetUtils.CHARSET_UTF_8);
    }


    /**
     * 32位MD5转16位MD5
     *
     * @param md5Hex 32位MD5
     * @return 16位MD5
     */
    public static String md5HexTo16(String md5Hex) {
        return md5Hex.substring(8, 24);
    }

    /**
     * 计算SHA-1摘要值
     *
     * @param data 被摘要数据
     * @return SHA-1摘要
     */
    public static byte[] sha1(byte[] data) {
        return new Digester(DigestAlgorithm.SHA1).digest(data);
    }

    /**
     * 计算SHA-1摘要值
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return SHA-1摘要
     */
    public static byte[] sha1(String data, String charset) {
        return new Digester(DigestAlgorithm.SHA1).digest(data, CharsetUtils.forName(charset));
    }

    /**
     * 计算sha1摘要值，使用UTF-8编码
     *
     * @param data 被摘要数据
     * @return MD5摘要
     */
    public static byte[] sha1(String data) {
        return sha1(data, CharsetUtils.UTF_8);
    }


    /**
     * 计算SHA-1摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return SHA-1摘要的16进制表示
     */
    public static String sha1Hex(byte[] data) {
        return new Digester(DigestAlgorithm.SHA1).digestHex(data);
    }

    /**
     * 计算SHA-1摘要值，并转为16进制字符串
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return SHA-1摘要的16进制表示
     */
    public static String sha1Hex(String data, String charset) {
        return new Digester(DigestAlgorithm.SHA1).digestHex(data, charset);
    }

    /**
     * 计算SHA-1摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return SHA-1摘要的16进制表示
     */
    public static String sha1Hex(String data) {
        return sha1Hex(data, CharsetUtils.UTF_8);
    }

    /**
     * 计算SHA-256摘要值
     *
     * @param data 被摘要数据
     * @return SHA-256摘要
     */
    public static byte[] sha256(byte[] data) {
        return new Digester(DigestAlgorithm.SHA256).digest(data);
    }

    /**
     * 计算SHA-256摘要值
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return SHA-256摘要
     */
    public static byte[] sha256(String data, String charset) {
        return new Digester(DigestAlgorithm.SHA256).digest(data, CharsetUtils.forName(charset));
    }

    /**
     * 计算sha256摘要值，使用UTF-8编码
     *
     * @param data 被摘要数据
     * @return SHA-256摘要
     */
    public static byte[] sha256(String data) {
        return sha256(data, CharsetUtils.UTF_8);
    }


    /**
     * 计算SHA-1摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return SHA-256摘要的16进制表示
     */
    public static String sha256Hex(byte[] data) {
        return new Digester(DigestAlgorithm.SHA256).digestHex(data);
    }

    /**
     * 计算SHA-256摘要值，并转为16进制字符串
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return SHA-256摘要的16进制表示
     */
    public static String sha256Hex(String data, String charset) {
        return new Digester(DigestAlgorithm.SHA256).digestHex(data, charset);
    }


    /**
     * 新建摘要器
     *
     * @param algorithm 签名算法
     * @return Digester
     */
    public static Digester digester(DigestAlgorithm algorithm) {
        return new Digester(algorithm);
    }

    /**
     * 新建摘要器
     *
     * @param algorithm 签名算法
     * @return Digester
     */
    public static Digester digester(String algorithm) {
        return new Digester(algorithm);
    }

    /**
     * 生成Bcrypt加密后的密文
     *
     * @param password 明文密码
     * @return 加密后的密文
     */
    public static String bcrypt(String password) {
        return BCrypt.hashpw(password);
    }

    /**
     * 验证密码是否与Bcrypt加密后的密文匹配
     *
     * @param password 明文密码
     * @param hashed   hash值（加密后的值）
     * @return 是否匹配
     */
    public static boolean bcryptCheck(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }

    // ------------------------------------------------------------------------------------------- SHA-512

    /**
     * 计算SHA-512摘要值
     *
     * @param data 被摘要数据
     * @return SHA-512摘要
     */
    public static byte[] sha512(final byte[] data) {
        return new Digester(DigestAlgorithm.SHA512).digest(data);
    }


    /**
     * 计算SHA-1摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return SHA-512摘要的16进制表示
     */
    public static String sha512Hex(final byte[] data) {
        return new Digester(DigestAlgorithm.SHA512).digestHex(data);
    }

    /**
     * 计算SHA-512摘要值，并转为16进制字符串
     *
     * @param data    被摘要数据
     * @param charset 编码
     * @return SHA-512摘要的16进制表示
     */
    public static String sha512Hex(final String data, final String charset) {
        return new Digester(DigestAlgorithm.SHA512).digestHex(data, charset);
    }

    /**
     * 计算SHA-512摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return SHA-512摘要的16进制表示
     */
    public static String sha512Hex(final String data) {
        return sha512Hex(data, CharsetUtils.CHARSET_UTF_8.name());
    }

}
