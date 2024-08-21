package com.ziyao.ideal.uua.service.security;

import com.ziyao.ideal.security.core.Authentication;

/**
 * @author ziyao zhang
 */
public interface AuthorizationCenter {

    /**
     * 授权
     */
    Object authorize(Integer appId, String state, String grantType);

    /**
     * 通过授权码或刷新token获取认证token
     *
     * @param authentication 授权对象
     * @return {@link com.ziyao.ideal.security.oauth2.core.OAuth2AccessToken}
     */
    Object token(Authentication authentication);
}
