package com.ziyao.ideal.uaa.authentication.token;

import com.ziyao.ideal.security.oauth2.core.AuthorizationGrantType;
import com.ziyao.ideal.uaa.authentication.provider.OAuth2AuthorizationCodeAuthenticationProvider;
import com.ziyao.ideal.security.core.Authentication;
import lombok.Getter;
import org.springframework.util.Assert;

/**
 * @author ziyao zhang
 * @see OAuth2AuthorizationGrantAuthenticationToken
 * @see OAuth2AuthorizationCodeAuthenticationProvider
 */
@Getter
public class OAuth2AuthorizationCodeAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    private static final long serialVersionUID = -4774441664615838808L;

    private final String code;

    private final String redirectUri;

    public OAuth2AuthorizationCodeAuthenticationToken(Authentication appPrincipal, String code, String redirectUri) {
        super(AuthorizationGrantType.AUTHORIZATION_CODE, appPrincipal);
        Assert.hasText(code, "code cannot be empty");
        this.code = code;
        this.redirectUri = redirectUri;
    }
}
