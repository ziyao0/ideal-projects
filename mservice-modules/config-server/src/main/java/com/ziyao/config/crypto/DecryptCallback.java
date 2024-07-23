package com.ziyao.config.crypto;

/**
 * 解密回调
 * <p>
 * 在文本需要解密时回调用该方法，
 * 实现自定义加密算法时需要实现该接口并通过{@code SPI}的方式加载实现的类
 * 为有优先级来划分，取优先级最高的为实际解密的方案。
 *
 * @author ziyao
 */
public interface DecryptCallback {

    /**
     * 解密
     *
     * @param encrypt 加密原文
     * @return 返回解密后的信息
     * @throws Exception 解密异常
     */
    String decrypt(String encrypt) throws Exception;
}
