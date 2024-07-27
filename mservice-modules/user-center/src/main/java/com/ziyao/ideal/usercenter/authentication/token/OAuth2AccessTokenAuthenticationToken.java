package com.ziyao.ideal.usercenter.authentication.token;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.GrantedAuthority;
import com.ziyao.ideal.security.oauth2.core.OAuth2AccessToken;
import com.ziyao.ideal.security.oauth2.core.OAuth2RefreshToken;
import com.ziyao.ideal.security.oauth2.core.RegisteredApp;
import lombok.Getter;

import java.io.Serial;
import java.util.Collection;
import java.util.Set;

/**
 * @author ziyao
 */
@Getter
public class OAuth2AccessTokenAuthenticationToken extends AbstractAuthenticationToken {

    @Serial
    private static final long serialVersionUID = -3997191320888560056L;
    /**
     * 经过认证的用法身份信息
     */
    private final Authentication appPrincipal;
    /**
     * 当前申请token的应用信息
     */
    private final RegisteredApp registeredApp;
    /**
     * 访问令牌
     */
    private final OAuth2AccessToken accessToken;
    /**
     * 刷新令牌
     */
    private final OAuth2RefreshToken refreshToken;


    public OAuth2AccessTokenAuthenticationToken(Authentication appPrincipal,
                                                RegisteredApp registeredApp, OAuth2AccessToken accessToken) {
        this(appPrincipal, registeredApp, accessToken, null);
    }


    public OAuth2AccessTokenAuthenticationToken(Authentication appPrincipal, RegisteredApp registeredApp,
                                                OAuth2AccessToken accessToken, OAuth2RefreshToken refreshToken) {
        this(appPrincipal, registeredApp, accessToken, refreshToken, Set.of());
    }

    public OAuth2AccessTokenAuthenticationToken(Authentication appPrincipal, RegisteredApp registeredApp,
                                                OAuth2AccessToken accessToken, OAuth2RefreshToken refreshToken, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.appPrincipal = appPrincipal;
        this.registeredApp = registeredApp;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Override
    public Object getPrincipal() {
        return appPrincipal;
    }

    @Override
    public Object getCredentials() {
        return Strings.EMPTY;
    }
}
