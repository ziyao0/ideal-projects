package com.ziyao.ideal.crypto.digest;

import com.ziyao.ideal.crypto.Algorithm;

import java.io.Serial;

/**
 * @author ziyao zhang
 * 
 */
public class SM3 extends Digester {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 创建SM3实例
     *
     * @return SM3
     */
    public static SM3 create() {
        return new SM3();
    }

    /**
     * 构造
     */
    public SM3() {
        super(Algorithm.SM3);
    }

}
