package com.ziyao.ideal.gateway.core;

/**
 * @author ziyao zhang
 */
public interface AuthorizerManager {

    /**
     * 获取授权方
     *
     * @param name 授权方名称
     * @return {@link Authorizer}
     */
    Authorizer getAuthorizer(String name);
}
