package com.ziyao.ideal.crypto;

/**
 * @author ziyao zhang
 */
public interface Algorithm {

    String SM2 = "SM2";
    String SM3 = "SM3";
    String SM4 = "SM4";

    /**
     * @return 算法名称
     */
    String getAlgorithm();
}
