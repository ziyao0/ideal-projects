package com.ziyao.harbor.im.core;

/**
 * @author ziyao zhang
 */
public interface DispatchHolder {


    /**
     * Implement this method to dynamically add a handler
     */
    default void addLast() {
    }
}
