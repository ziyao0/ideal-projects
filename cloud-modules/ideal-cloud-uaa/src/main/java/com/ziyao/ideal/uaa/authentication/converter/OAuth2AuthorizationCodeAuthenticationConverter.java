package com.ziyao.ideal.uaa.authentication.converter;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.context.SecurityContextHolder;
import com.ziyao.ideal.security.oauth2.core.AuthorizationGrantType;
import com.ziyao.ideal.security.oauth2.core.error.OAuth2ErrorCodes;
import com.ziyao.ideal.security.oauth2.core.support.OAuth2EndpointUtils;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2ParameterNames;
import com.ziyao.ideal.uaa.authentication.token.OAuth2AuthorizationCodeAuthenticationToken;
import com.ziyao.ideal.uaa.request.AuthenticationRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ziyao
 */
public class OAuth2AuthorizationCodeAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(AuthenticationRequest request) {

        if (!AuthorizationGrantType.AUTHORIZATION_CODE.matches(request.getGrantType())) {
            return null;
        }

        Authentication appPrincipal = SecurityContextHolder.getContext().getAuthentication();

        String code = request.getCode();

        if (!Strings.hasText(code)) {
            OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.CODE,
                    OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
        }

        String redirectUri = request.getRedirectUri();


        return new OAuth2AuthorizationCodeAuthenticationToken(appPrincipal, code, redirectUri);
    }

    @Override
    public Authentication convert(HttpServletRequest request) {
        if (!AuthorizationGrantType.AUTHORIZATION_CODE.matches(request.getParameter(OAuth2ParameterNames.GRANT_TYPE))) {
            return null;
        }

        Authentication appPrincipal = SecurityContextHolder.getContext().getAuthentication();

        String code = request.getParameter(OAuth2ParameterNames.CODE);

        if (!Strings.hasText(code)) {
            OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.CODE,
                    OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
        }

        String redirectUri = request.getParameter(OAuth2ParameterNames.REDIRECT_URI);

        return new OAuth2AuthorizationCodeAuthenticationToken(appPrincipal, code, redirectUri);
    }
}
