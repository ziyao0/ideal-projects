package com.ziyao.ideal.security.oauth2.core.client;

import com.ziyao.ideal.security.oauth2.core.OAuth2AccessToken;
import com.ziyao.ideal.security.oauth2.core.OAuth2RefreshToken;
import lombok.Getter;


import java.io.Serializable;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class OAuth2AuthorizationClient implements Serializable {

    
    private static final long serialVersionUID = 1061687380882245619L;


    private final ClientRegistration clientRegistration;

    private final Object principalName;

    private final OAuth2AccessToken accessToken;

    private final OAuth2RefreshToken refreshToken;

    public OAuth2AuthorizationClient(ClientRegistration clientRegistration, String principalName,
                                     OAuth2AccessToken accessToken) {
        this(clientRegistration, principalName, accessToken, null);
    }

    public OAuth2AuthorizationClient(ClientRegistration clientRegistration, Object principalName,
                                     OAuth2AccessToken accessToken, OAuth2RefreshToken refreshToken) {
        this.clientRegistration = clientRegistration;
        this.principalName = principalName;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
