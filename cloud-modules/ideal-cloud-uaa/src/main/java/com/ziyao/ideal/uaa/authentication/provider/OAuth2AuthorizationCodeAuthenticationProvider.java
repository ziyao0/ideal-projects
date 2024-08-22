package com.ziyao.ideal.uaa.authentication.provider;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.oauth2.core.*;
import com.ziyao.ideal.security.oauth2.core.error.ThrowErrors;
import com.ziyao.ideal.security.oauth2.core.token.DefaultOAuth2TokenContext;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2ParameterNames;
import com.ziyao.ideal.uaa.authentication.token.OAuth2AccessTokenAuthenticationToken;
import com.ziyao.ideal.uaa.authentication.token.OAuth2AuthorizationCodeAuthenticationToken;
import com.ziyao.ideal.uaa.authentication.token.OAuth2TokenGenerator;
import com.ziyao.ideal.uaa.service.app.RegisteredAppService;
import com.ziyao.ideal.uaa.service.oauth2.OAuth2AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ziyao zhang
 */
@Slf4j
@Component
public class OAuth2AuthorizationCodeAuthenticationProvider implements AuthenticationProvider {

    private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";

    private final OAuth2AuthorizationService authorizationService;

    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;

    private final RegisteredAppService registeredAppService;

    public OAuth2AuthorizationCodeAuthenticationProvider(OAuth2AuthorizationService authorizationService,
                                                         OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator,
                                                         RegisteredAppService registeredAppService) {
        this.authorizationService = authorizationService;
        this.tokenGenerator = tokenGenerator;
        this.registeredAppService = registeredAppService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        OAuth2AuthorizationCodeAuthenticationToken codeAuthenticationToken = (OAuth2AuthorizationCodeAuthenticationToken) authentication;

        Authentication principal = (Authentication) codeAuthenticationToken.getPrincipal();
        // 授权码
        String code = codeAuthenticationToken.getCode();

        OAuth2Authorization authorization = authorizationService.findByToken(code, new OAuth2TokenType(OAuth2ParameterNames.CODE));
        if (authorization == null) {
            ThrowErrors.invalidGrantError(code);
        }
        Optional<OAuth2Authorization.Token<OAuth2AuthorizationCode>> tokenOptional = Optional.ofNullable(authorization.getToken(OAuth2AuthorizationCode.class));


        if (!tokenOptional.map(OAuth2Authorization.Token::isActive).orElse(false)) {
            ThrowErrors.invalidGrantError(code);
        }

        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.from(authorization);

        OAuth2AuthorizationCode authorizationCode = tokenOptional.get().getToken();
        // 验证
        RegisteredApp registeredApp = registeredAppService.findById(authorization.getAppId());

        if (null == registeredApp) {
            ThrowErrors.invalidClientError(authorization.getAppId().toString());
        }

        DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                .authorization(authorization)
                .registeredApp(registeredApp);

        // 生成访问令牌
        DefaultOAuth2TokenContext accessTokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();

        OAuth2Token generatedAccessToken = this.tokenGenerator.generate(accessTokenContext);

        OAuth2AccessToken accessToken = OAuth2AuthenticationUtils.accessToken(authorizationBuilder, generatedAccessToken, accessTokenContext);

        // 生成刷新token
        OAuth2RefreshToken refreshToken = null;

        if (registeredApp.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN)) {

            DefaultOAuth2TokenContext refreshTokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
            OAuth2Token generatedRefreshToken = this.tokenGenerator.generate(refreshTokenContext);
            if (generatedRefreshToken != null) {
                if (!(generatedRefreshToken instanceof OAuth2RefreshToken)) {
                    ThrowErrors.serverError("The token generator failed to generate a valid refresh token.", ERROR_URI);
                }
                if (log.isDebugEnabled()) {
                    log.debug("Generated Refresh token: {}", generatedRefreshToken);
                }
                refreshToken = (OAuth2RefreshToken) generatedRefreshToken;
                authorizationBuilder.refreshToken(refreshToken);
            }
        }

        // 更新授权信息
        OAuth2Authorization updateAuthorization = OAuth2AuthenticationUtils.invalidate(authorizationBuilder.build(), authorizationCode);

        authorizationService.save(updateAuthorization);

        return new OAuth2AccessTokenAuthenticationToken(
                principal, registeredApp, accessToken, refreshToken);
    }


    @Override
    public boolean supports(Class<?> authenticationClass) {
        return OAuth2AuthorizationCodeAuthenticationToken.class.isAssignableFrom(authenticationClass);
    }
}
