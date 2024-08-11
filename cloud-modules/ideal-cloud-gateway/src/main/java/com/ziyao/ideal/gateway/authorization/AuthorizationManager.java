package com.ziyao.ideal.gateway.authorization;

import com.ziyao.ideal.gateway.authorization.token.Authorization;

/**
 * @author ziyao zhang
 */
public interface AuthorizationManager {

    /**
     * 获取授权方
     *
     * @param name 授权方名称
     * @return {@link Authorization}
     */
    AuthorizationProvider getAuthorization(String name);
}
