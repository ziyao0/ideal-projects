package com.ziyao.ideal.crypto;

/**
 * @author ziyao zhang
 */
public abstract class AbstractAlgorithm implements Algorithm {


    private final String algorithm;

    public AbstractAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public String getAlgorithm() {
        return this.algorithm;
    }
}
