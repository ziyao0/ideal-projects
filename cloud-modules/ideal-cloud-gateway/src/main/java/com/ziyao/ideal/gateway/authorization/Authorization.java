package com.ziyao.ideal.gateway.authorization;

import com.ziyao.ideal.security.core.SessionUser;

import java.util.Optional;

/**
 * @author ziyao zhang
 */
public interface Authorization {

    /**
     * 获取当前设备IP
     *
     * @return IP
     */
    String getIp();

    /**
     * 获取当前路由信息
     *
     * @return 获取路由信息
     */
    String getResource();

    /**
     * 获取请求地址
     *
     * @return request uri
     */
    String getRequestPath();

    /**
     * 访问域
     *
     * @return domain
     */
    String getDomain();

    /**
     * 获取当前用户的会话信息
     *
     * @return 返回会话用户信息
     */
    Optional<SessionUser> getPrincipal();

    boolean isAuthorized();

    void setAuthorized(boolean authorized);
}