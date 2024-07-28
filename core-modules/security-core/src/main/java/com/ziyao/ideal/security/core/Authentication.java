package com.ziyao.ideal.security.core;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author ziyao
 */
public interface Authentication extends Serializable {

    /**
     * 获取主体信息
     * <p>
     * 可以为用户名、用户信息
     *
     * @return 返回主体信息
     */
    Object getPrincipal();

    /**
     * 获取用户凭证
     *
     * @return 返回用户凭证
     */
    Object getCredentials();

    /**
     * 获取赋予用户的权限
     *
     * @return 用户拥有的权限集合
     */
    Collection<? extends GrantedAuthority> getAuthorities();

    /**
     * 返回当前认证对象是否经过认证
     *
     * @return <code>true</code>以经过身份认证
     */
    boolean isAuthenticated();

    /**
     * 设置认证状态
     *
     * @param authenticated 认证状态 <code>true</code> 为已经通过认证
     */
    void setAuthenticated(boolean authenticated);
}
