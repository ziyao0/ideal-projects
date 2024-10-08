package com.ziyao.ideal.security.core.context;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.User;

import java.io.Serializable;

/**
 * @author ziyao zhang
 */
public interface SecurityContext extends Serializable {

    default User getPrincipal() {
        Authentication authentication = getAuthentication();
        return authentication != null && authentication.isAuthenticated() ? (User) authentication.getPrincipal() : null;
    }

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
     * 获取 claims
     *
     * @return {@link PrincipalClaims}
     */
    UserClaims getClaims();

    /**
     * 更改当前 claims
     *
     * @param userClaims {@link PrincipalClaims}
     */
    void setUserClaims(UserClaims userClaims);

    default String getIp() {
        return getClaims().getIp();
    }

    default String getLocation() {
        return getClaims().getLocation();
    }
}
