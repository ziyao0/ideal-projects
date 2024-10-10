package com.ziyao.ideal.security.core.context;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.GrantedAuthority;
import com.ziyao.ideal.security.core.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * @author ziyao zhang
 */
public interface SecurityContext extends Serializable {


    /**
     * 获取当前经过身份认证的令牌
     *
     * @return 如果返回null则表示当前请求没有经过身份认证
     */
    Authentication getAuthentication();

    /**
     * 更改当前经过身份验证的主体，或删除身份验证信息。
     */
    void setAuthentication(Authentication authentication);

    /**
     * 获取当前主体的用户信息
     *
     * @return {@link User}
     */
    default User getPrincipal() {
        Authentication authentication = getAuthentication();
        return authentication != null && authentication.isAuthenticated() ? (User) authentication.getPrincipal() : null;
    }

    /**
     * 获取当前用户的权限认证信息
     *
     * @return {@link GrantedAuthority}
     */
    default Collection<? extends GrantedAuthority> getAuthorities() {
        Authentication authentication = getAuthentication();
        return authentication != null && authentication.isAuthenticated() ? authentication.getAuthorities() : Set.of();
    }
}
