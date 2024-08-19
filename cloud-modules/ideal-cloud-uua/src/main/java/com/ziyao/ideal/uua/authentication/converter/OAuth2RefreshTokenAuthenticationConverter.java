package com.ziyao.ideal.uua.authentication.converter;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.context.SecurityContextHolder;
import com.ziyao.ideal.security.oauth2.core.AuthorizationGrantType;
import com.ziyao.ideal.security.oauth2.core.error.OAuth2ErrorCodes;
import com.ziyao.ideal.security.oauth2.core.support.OAuth2EndpointUtils;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2ParameterNames;
import com.ziyao.ideal.uua.authentication.token.OAuth2RefreshTokenAuthenticationToken;
import com.ziyao.ideal.uua.request.AuthenticationRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Set;

/**
 * @author ziyao
 */
public class OAuth2RefreshTokenAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(AuthenticationRequest request) {

        if (!AuthorizationGrantType.REFRESH_TOKEN.matches(request.getGrantType())) {
            return null;
        }

        String refreshToken = request.getRefreshToken();

        if (!Strings.hasText(refreshToken)) {
            OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.REFRESH_TOKEN,
                    OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Set<String> scopes = request.getScopes();

        return new OAuth2RefreshTokenAuthenticationToken(refreshToken, authentication, scopes);
    }

    @Override
    public Authentication convert(HttpServletRequest request) {

        if (AuthorizationGrantType.REFRESH_TOKEN.matches(request.getParameter(OAuth2ParameterNames.GRANT_TYPE))) {
            String refreshToken = request.getParameter(OAuth2ParameterNames.REFRESH_TOKEN);

            if (!Strings.hasText(refreshToken)) {
                OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.REFRESH_TOKEN,
                        OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String scope = request.getParameter(OAuth2ParameterNames.SCOPE);

            return new OAuth2RefreshTokenAuthenticationToken(refreshToken, authentication, Sets.newHashSet(scope));
        }

        return null;
    }
}
