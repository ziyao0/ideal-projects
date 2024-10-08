package com.ziyao.ideal.gateway.service;

import com.ziyao.ideal.security.core.User;

import java.util.Optional;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface PrincipalCacheService {

    /**
     * 加载会话信息
     *
     * @param token 会话ID
     * @return 返回会话信息
     */
    Optional<User> load(String token);

    Optional<User> loadAndRefresh(String token);

    /**
     * 存储会话信息
     *
     * @param token 凭证
     * @param user  待保存的会话信息
     */
    void save(String token, User user);

    /**
     * 注销会话
     *
     * @param token 会话ID
     */
    void remove(String token);

    /**
     * 刷新过期时间
     */
    void refresh(String token, long ttl);

    void initialize();
}
