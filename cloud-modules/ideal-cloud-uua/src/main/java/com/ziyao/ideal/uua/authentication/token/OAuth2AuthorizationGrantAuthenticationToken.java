package com.ziyao.ideal.uua.authentication.token;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.core.AbstractAuthenticationToken;
import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.oauth2.core.AuthorizationGrantType;


import java.util.List;

/**
 * @author ziyao zhang
 */
public class OAuth2AuthorizationGrantAuthenticationToken extends AbstractAuthenticationToken {

    
    private static final long serialVersionUID = -2867200150211134714L;

    private final AuthorizationGrantType authorizationGrantType;

    private final Authentication appPrincipal;

    public OAuth2AuthorizationGrantAuthenticationToken(AuthorizationGrantType authorizationGrantType, Authentication appPrincipal) {
        super(Lists.newArrayList());
        this.authorizationGrantType = authorizationGrantType;
        this.appPrincipal = appPrincipal;
    }

    @Override
    public Object getPrincipal() {
        return appPrincipal;
    }

    @Override
    public Object getCredentials() {
        return Strings.EMPTY;
    }

    public AuthorizationGrantType getGrantType() {
        return this.authorizationGrantType;
    }
}
