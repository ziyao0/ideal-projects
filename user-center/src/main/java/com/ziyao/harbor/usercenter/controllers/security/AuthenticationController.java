package com.ziyao.harbor.usercenter.controllers.security;

import com.ziyao.harbor.usercenter.authentication.converter.AuthenticationConverter;
import com.ziyao.harbor.usercenter.authentication.token.FailureAuthentication;
import com.ziyao.harbor.usercenter.service.security.AuthenticationService;
import com.ziyao.harbor.web.ResponseBuilder;
import com.ziyao.harbor.web.response.ResponseWrapper;
import com.ziyao.security.oauth2.core.Authentication;
import com.ziyao.security.oauth2.core.support.AuthenticationUtils;
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
            FailureAuthentication failure = (FailureAuthentication) authenticated;
            return ResponseBuilder.of(failure.getStatus(), failure.getMessage(), failure);
        }
    }

    @GetMapping("verify")
    public String verify() {
        return "verify";
    }

}
