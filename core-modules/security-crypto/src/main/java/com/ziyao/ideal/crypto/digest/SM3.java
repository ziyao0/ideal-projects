package com.ziyao.ideal.crypto.digest;

import com.ziyao.ideal.crypto.Algorithm;



/**
 * @author ziyao zhang
 * @since 2023/10/19
 */
public class SM3 extends Digester {
    
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
