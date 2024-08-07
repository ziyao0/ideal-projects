package com.ziyao.ideal.web.orm;

/**
 * @author ziyao zhang
 */
public interface EntityDTO<T> {

    default Integer getId() {
        return null;
    }

}
