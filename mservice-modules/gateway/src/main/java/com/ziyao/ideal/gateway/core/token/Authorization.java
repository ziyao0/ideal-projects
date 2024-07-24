package com.ziyao.ideal.gateway.core.token;

import java.io.Serializable;

/**
 * @author ziyao zhang
 */
public interface Authorization extends Serializable {
    String getToken();

    default boolean isSecurity() {
        return false;
    }

    default boolean isAuthorized() {
        return false;
    }

    default String getMessage() {
        return null;
    }

}