package com.ziyao.ideal.uua.service.security;

import com.ziyao.ideal.uua.response.AccessTokenResponse;
import com.ziyao.ideal.uua.response.OAuth2AuthorizationCodeResponse;
import com.ziyao.ideal.security.core.Authentication;

/**
 * @author ziyao zhang
 */
public interface AuthorizationCenter {

    /**
     * 授权
     */
    OAuth2AuthorizationCodeResponse authorize(Integer appId, String state, String grantType);

    /**
     * 通过授权码或刷新token获取认证token
     *
     * @param authentication 授权对象
     * @return {@link com.ziyao.ideal.security.oauth2.core.OAuth2AccessToken}
     */
    AccessTokenResponse token(Authentication authentication);
}
