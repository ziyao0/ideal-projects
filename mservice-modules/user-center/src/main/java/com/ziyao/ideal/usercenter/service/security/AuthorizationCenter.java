package com.ziyao.ideal.usercenter.service.security;

import com.ziyao.ideal.usercenter.response.AccessTokenResponse;
import com.ziyao.ideal.usercenter.response.OAuth2AuthorizationCodeResponse;
import com.ziyao.ideal.security.core.Authentication;

/**
 * @author ziyao zhang
 */
public interface AuthorizationCenter {

    /**
     * 授权
     */
    OAuth2AuthorizationCodeResponse authorize(Long appId, String state, String grantType);

    /**
     * 通过授权码或刷新token获取认证token
     *
     * @param authentication 授权对象
     * @return {@link com.ziyao.security.oauth2.core.OAuth2AccessToken}
     */
    AccessTokenResponse token(Authentication authentication);
}
