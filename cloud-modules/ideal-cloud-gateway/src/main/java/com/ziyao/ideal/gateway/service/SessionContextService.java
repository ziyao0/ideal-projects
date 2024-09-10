package com.ziyao.ideal.gateway.service;

import com.ziyao.ideal.security.core.SessionUser;

import java.util.Optional;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface SessionContextService {

    /**
     * 加载会话信息
     *
     * @param sessionId 会话ID
     * @return 返回会话信息
     */
    Optional<SessionUser> load(String sessionId);

    /**
     * 存储会话信息
     *
     * @param sessionId   会话ID
     * @param sessionUser 待保存的会话信息
     */
    void save(String sessionId, SessionUser sessionUser);

    /**
     * 注销会话
     *
     * @param sessionId 会话ID
     */
    void remove(String sessionId);

    void initialize();
}
