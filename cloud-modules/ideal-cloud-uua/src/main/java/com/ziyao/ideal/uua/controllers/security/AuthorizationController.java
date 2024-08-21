package com.ziyao.ideal.uua.controllers.security;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.context.SecurityContextHolder;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2ParameterNames;
import com.ziyao.ideal.uua.authentication.converter.AuthenticationConverter;
import com.ziyao.ideal.uua.service.security.AuthorizationCenter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/oauth2")
public class AuthorizationController {

    private final AuthenticationConverter authenticationConverter;
    private final AuthorizationCenter authorizationCenter;

    /**
     * 授权
     */
    @GetMapping("/authorize")
    public Object authorize(HttpServletRequest request) {

        if (SecurityContextHolder.unauthorized()) {
            // 如果未授权则跳转发到认证服务器
        }

        Integer appId = Optional.ofNullable(request.getParameter(OAuth2ParameterNames.APP_ID)).map(Integer::parseInt).orElse(null);
        String state = Optional.ofNullable(request.getParameter(OAuth2ParameterNames.STATE)).orElse(Strings.EMPTY);
        String grantType = Optional.ofNullable(request.getParameter(OAuth2ParameterNames.GRANT_TYPE)).orElse(Strings.EMPTY);

        return authorizationCenter.authorize(appId, state, grantType);
    }

    @GetMapping("/token")
    public Object token(HttpServletRequest request) {
        try {
            Authentication authentication = authenticationConverter.convert(request);

            return authorizationCenter.token(authentication);
        } catch (Exception e) {
            log.error("{}", e.getMessage(), e);
            throw e;
        }

    }
}
