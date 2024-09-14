package com.ziyao.ideal.gateway.support;

import com.ziyao.ideal.gateway.authorization.Authorization;

/**
 * @author ziyao zhang
 */
public abstract class SecurityUtils {

    public static boolean authorized(Authorization authorization) {
        return authorization != null && authorization.isAuthorized();
    }

    public static boolean unauthorized(Authorization authorization) {
        return !authorized(authorization);
    }

    private SecurityUtils() {
    }
}
