package com.ziyao.ideal.gateway.security;

import com.ziyao.ideal.security.core.UserInfo;

import java.io.Serializable;

/**
 * @author ziyao zhang
 */
public interface Authorization extends Serializable {

    String getToken();

    String getRefreshToken();

    default UserInfo getPrincipal() {
        return null;
    }

    boolean isAuthorized();

    void setAuthorized(boolean authorized);
}