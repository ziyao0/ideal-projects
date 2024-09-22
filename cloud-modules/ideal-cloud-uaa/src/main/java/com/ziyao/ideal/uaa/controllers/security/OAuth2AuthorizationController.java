package com.ziyao.ideal.uaa.controllers.security;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.context.SecurityContextHolder;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2ParameterNames;
import com.ziyao.ideal.uaa.authentication.converter.AuthenticationConverter;
import com.ziyao.ideal.uaa.response.AccessTokenResponse;
import com.ziyao.ideal.uaa.response.OAuth2AuthorizationCodeResponse;
import com.ziyao.ideal.uaa.service.security.AuthorizationCenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/oauth")
@Tag(name = "授权控制器", description = "授权控制器")
public class OAuth2AuthorizationController {

    private final AuthenticationConverter authenticationConverter;
    private final AuthorizationCenter authorizationCenter;

    /**
     * 授权
     */
    @GetMapping("/authorize")
    @Operation(summary = "获取授权码", description = "获取授权码")
    public OAuth2AuthorizationCodeResponse authorize(HttpServletRequest request) {

        if (SecurityContextHolder.unauthorized()) {
            // 如果未授权则跳转发到认证服务器
        }

        Integer appId = Optional.ofNullable(request.getParameter(OAuth2ParameterNames.APP_ID)).map(Integer::parseInt).orElse(null);
        String state = Optional.ofNullable(request.getParameter(OAuth2ParameterNames.STATE)).orElse(Strings.EMPTY);
        String grantType = Optional.ofNullable(request.getParameter(OAuth2ParameterNames.GRANT_TYPE)).orElse(Strings.EMPTY);

        return authorizationCenter.authorize(appId, state, grantType);
    }

    @GetMapping("/token")
    @Operation(summary = "获取访问令牌", description = "获取访问令牌")
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
