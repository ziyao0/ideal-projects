package com.ziyao.ideal.gateway.security;

import com.ziyao.ideal.security.core.SessionUser;

import java.util.Optional;

/**
 * 鉴权
 *
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface SessionContext {
    /**
     * 获取当前设备IP
     *
     * @return IP
     */
    String getIp();

    /**
     * 获取当前会话的唯一标识
     *
     * @return 会话ID
     */
    String getSessionId();

    /**
     * 获取当前路由信息
     *
     * @return 获取路由信息
     */
    String getResourceUri();

    /**
     * 获取请求地址
     *
     * @return request uri
     */
    String getRequestUri();

    /**
     * 访问接口的时间戳
     *
     * @return timestamp
     */
    String getTimestamp();

    /**
     * 摘要信息
     *
     * @return digest
     */
    String getDigest();

    /**
     * 访问令牌
     *
     * @return token
     */
    String getToken();

    /**
     * 刷新令牌
     *
     * @return refresh token
     */
    String getRefreshToken();

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
    Optional<SessionUser> getSessionUser();
}
