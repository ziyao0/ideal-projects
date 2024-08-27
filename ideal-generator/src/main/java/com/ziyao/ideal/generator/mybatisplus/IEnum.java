package com.ziyao.ideal.generator.mybatisplus;

import java.io.Serializable;

/**
 * 自定义枚举接口
 */
public interface IEnum<T extends Serializable> {

    /**
     * 枚举数据库存储值
     */
    T getValue();
}
