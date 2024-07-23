package com.ziyao.config.crypto;

/**
 * 加密回调
 * <p>
 * 在文本需要加密时回调用该方法，
 * 实现自定义加密算法时需要实现该接口并通过{@code SPI}的方式加载实现的类
 * 为有优先级来划分，取优先级最高的为实际加密的方案。
 *
 * @author ziyao
 */
public interface EncryptCallback {

    /**
     * 加密传入的对象属性
     *
     * @param text 代加密文本
     * @return 返回加密后的密文信息
     * @throws Exception 加密异常
     */
    String encrypt(String text) throws Exception;

}
