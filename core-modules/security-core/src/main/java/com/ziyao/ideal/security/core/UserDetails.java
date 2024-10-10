package com.ziyao.ideal.security.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author zhangziyao
 */
public interface UserDetails extends Serializable {

    /**
     * 用于校验的用户名
     *
     * @return username
     */
    String getUsername();

    /**
     * 用于校验的密码
     *
     * @return pd
     */
    @JsonIgnore
    String getPassword();

    /**
     * 表示账号是否过期，过期的账号不能登录
     *
     * @return <code>true</code> 没有过期, <code>false</code> 已过期
     */
    @JsonIgnore
    boolean isAccountNonExpired();

    /**
     * 标识账号是否被锁定，被锁定的账号无法进行认证
     *
     * @return <code>true</code> not locked, <code>false</code> locked
     */
    @JsonIgnore
    boolean isAccountNonLocked();

    /**
     * 用户密码是否过期，过期密码无法校验
     *
     * @return <code>true</code> 用户凭证有效（没有过期）, <code>false</code> 过期
     */
    @JsonIgnore
    boolean isCredentialsNonExpired();

    /**
     * 提示账号是启用还是禁用，已禁用的账号是无法登录的
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @JsonIgnore
    boolean isEnabled();

}
