package com.ziyao.ideal.uaa.service.security;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.oauth2.core.*;
import com.ziyao.ideal.security.core.UserDetails;
import com.ziyao.ideal.security.oauth2.core.token.DefaultOAuth2TokenContext;
import com.ziyao.ideal.uaa.authentication.AuthenticationManager;
import com.ziyao.ideal.uaa.authentication.token.OAuth2AccessTokenAuthenticationToken;
import com.ziyao.ideal.uaa.authentication.token.OAuth2TokenGenerator;
import com.ziyao.ideal.uaa.response.AccessTokenResponse;
import com.ziyao.ideal.uaa.response.OAuth2AuthorizationCodeResponse;
import com.ziyao.ideal.uaa.service.app.RegisteredAppService;
import com.ziyao.ideal.uaa.service.oauth2.OAuth2AuthorizationService;
import com.ziyao.ideal.security.core.context.SecurityContextHolder;
import com.ziyao.ideal.security.core.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

/**
 * @author ziyao zhang
 */
@Service
@RequiredArgsConstructor
public class PrincipalAuthorizationCenter implements AuthorizationCenter {

    private final AuthenticationManager authenticationManager;
    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;
    private final RegisteredAppService registeredAppService;
    private final OAuth2AuthorizationService authorizationService;

    @Override
    public OAuth2AuthorizationCodeResponse authorize(Integer appId, String state, String grantType) {

        RegisteredApp registeredApp = registeredAppService.findById(appId);
        if (registeredApp == null) {
            throw new DataRetrievalFailureException(
                    "The RegisteredClient with id '" + appId + "' was not found in the RegisteredClientRepository.");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        DefaultOAuth2TokenContext context = DefaultOAuth2TokenContext.builder()
                .tokenType(new OAuth2TokenType(grantType))
                .registeredApp(registeredApp)
                // 填充用户信息
                .principal(authentication)
                .build();

        OAuth2Token auth2Token = this.tokenGenerator.generate(context);
        if (null == auth2Token) {
            return null;
        }
        OAuth2AuthorizationCode authorizationCode = (OAuth2AuthorizationCode) auth2Token;

        UserDetails principal = (UserDetails) authentication.getPrincipal();

        OAuth2Authorization authorization = OAuth2Authorization.withAppId(registeredApp.getAppId())
//                .userId(principal.getId())
                .userId(10001)
                .token(authorizationCode)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .build();

        authorizationService.save(authorization);
        return OAuth2AuthorizationCodeResponse.create(authorizationCode.getTokenValue(), "");
    }

    @Override
    public AccessTokenResponse token(Authentication authentication) {


        OAuth2AccessTokenAuthenticationToken authenticationToken = (OAuth2AccessTokenAuthenticationToken) this.authenticationManager.authenticate(authentication);
        if (AuthenticationUtils.unauthenticated(authentication)) {
            // TODO
        }

        return AccessTokenResponse.witchTokenType(authenticationToken.getAccessToken().getTokenType().getValue())
                .accessToken(authenticationToken.getAccessToken().getTokenValue())
                .refreshToken(authenticationToken.getRefreshToken().getTokenValue())
                .build();
    }
}
