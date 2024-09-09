package com.ziyao.ideal.gateway.security;

import com.ziyao.ideal.security.core.SessionUser;

import java.io.Serializable;

/**
 * @author ziyao zhang
 */
public interface Authorization extends Serializable {

    String getToken();

    String getRefreshToken();

    default SessionUser getPrincipal() {
        return null;
    }

    boolean isAuthorized();

    void setAuthorized(boolean authorized);
}