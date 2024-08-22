package com.ziyao.ideal.uaa.controllers.security;

import com.ziyao.ideal.web.ResponseBuilder;
import com.ziyao.ideal.web.response.ResponseWrapper;
import com.ziyao.ideal.uaa.authentication.converter.AuthenticationConverter;
import com.ziyao.ideal.uaa.authentication.token.FailureAuthenticationToken;
import com.ziyao.ideal.uaa.service.security.AuthenticationService;
import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.AuthenticationUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationConverter authenticationConverter;

    @GetMapping("/login")
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
    public String verify() {
        return "verify";
    }

}
