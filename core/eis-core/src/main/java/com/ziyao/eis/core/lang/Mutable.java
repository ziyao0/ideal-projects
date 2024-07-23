package com.ziyao.eis.core.lang;

/**
 * @author ziyao zhang
 */
public interface Mutable<T> {

    /**
     * 获得原始值
     *
     * @return 原始值
     */
    T get();

    /**
     * 设置值
     *
     * @param value 值
     */
    void set(T value);

}