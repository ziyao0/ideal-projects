package com.ziyao.ideal.uua.authentication.converter;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.oauth2.core.AuthorizationGrantType;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2ParameterNames;
import com.ziyao.ideal.uua.authentication.token.UsernamePasswordAuthenticationToken;
import com.ziyao.ideal.uua.request.AuthenticationRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author ziyao
 */
public class UsernamePasswordAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(AuthenticationRequest request) {

        if (!AuthorizationGrantType.PASSWORD.matches(request.getGrantType())) {
            return null;
        }
        // 获取用户输入的用户名和密码
        String username = Optional.ofNullable(request.getUsername()).orElse(Strings.EMPTY);
        String password = Optional.ofNullable(request.getPassword()).orElse(Strings.EMPTY);

        return UsernamePasswordAuthenticationToken.unauthenticated(username, password);
    }

    @Override
    public Authentication convert(HttpServletRequest request) {
        if (!AuthorizationGrantType.PASSWORD.matches(request.getParameter(OAuth2ParameterNames.PASSWORD))) {
            return null;
        }

        // 获取用户输入的用户名和密码
        String username = Optional.ofNullable(request.getParameter(OAuth2ParameterNames.USERNAME)).orElse(Strings.EMPTY);
        String password = Optional.ofNullable(request.getParameter(OAuth2ParameterNames.PASSWORD)).orElse(Strings.EMPTY);

        return UsernamePasswordAuthenticationToken.unauthenticated(username, password);
    }
}
