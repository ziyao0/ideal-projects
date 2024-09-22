package com.ziyao.ideal.uaa.controllers.security;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.AuthenticationUtils;
import com.ziyao.ideal.uaa.authentication.converter.AuthenticationConverter;
import com.ziyao.ideal.uaa.authentication.token.FailureAuthenticationToken;
import com.ziyao.ideal.uaa.service.security.AuthenticationService;
import com.ziyao.ideal.web.ResponseBuilder;
import com.ziyao.ideal.web.response.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Tag(name = "权限认证功能", description = "权限认证功能")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationConverter authenticationConverter;

    @GetMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录")
    public ResponseWrapper<Authentication> login(HttpServletRequest request) {

        Authentication authentication = authenticationConverter.convert(request);

        Authentication authenticated = authenticationService.login(authentication);
        if (AuthenticationUtils.isAuthenticated(authenticated)) {
            return ResponseBuilder.ok(authenticated);
        } else {
            FailureAuthenticationToken failure = (FailureAuthenticationToken) authenticated;
            return ResponseBuilder.of(failure.getStatus(), failure.getMessage(), failure);
        }
    }

    @GetMapping("verify")
    @Operation(summary = "登录多因子验证", description = "登录多因子验证")
    public String verify() {
        return "verify";
    }

}
