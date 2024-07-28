package com.ziyao.ideal.uua.controllers.security;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2ParameterNames;
import com.ziyao.ideal.uua.authentication.converter.AuthenticationConverter;
import com.ziyao.ideal.uua.response.AccessTokenResponse;
import com.ziyao.ideal.uua.response.OAuth2AuthorizationCodeResponse;
import com.ziyao.ideal.uua.service.security.AuthorizationCenter;
import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.context.SecurityContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhangziyao
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthenticationConverter authenticationConverter;
    private final AuthorizationCenter authorizationCenter;

    /**
     * 授权
     */
    @GetMapping("/authorize")
    public OAuth2AuthorizationCodeResponse authorize(HttpServletRequest request) {

        if (!SecurityContextHolder.isUnauthorized()) {
            // 如果未授权则跳转发到认证服务器
        }

        Long appId = Optional.ofNullable(request.getParameter(OAuth2ParameterNames.APP_ID)).map(Long::parseLong).orElse(null);
        String state = Optional.ofNullable(request.getParameter(OAuth2ParameterNames.STATE)).orElse(Strings.EMPTY);
        String grantType = Optional.ofNullable(request.getParameter(OAuth2ParameterNames.GRANT_TYPE)).orElse(Strings.EMPTY);

        return authorizationCenter.authorize(appId, state, grantType);
    }

    @GetMapping("/token")
    public AccessTokenResponse token(HttpServletRequest request) {
        try {
            Authentication authentication = authenticationConverter.convert(request);

            return authorizationCenter.token(authentication);
        } catch (Exception e) {
            log.error("{}", e.getMessage(), e);
            throw e;
        }

    }
}
