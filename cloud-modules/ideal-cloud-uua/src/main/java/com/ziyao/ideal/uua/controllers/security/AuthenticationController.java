package com.ziyao.ideal.uua.controllers.security;

import com.ziyao.ideal.web.ResponseBuilder;
import com.ziyao.ideal.web.response.ResponseWrapper;
import com.ziyao.ideal.uua.authentication.converter.AuthenticationConverter;
import com.ziyao.ideal.uua.authentication.token.FailureAuthentication;
import com.ziyao.ideal.uua.service.security.AuthenticationService;
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
            FailureAuthentication failure = (FailureAuthentication) authenticated;
            return ResponseBuilder.of(failure.getStatus(), failure.getMessage(), failure);
        }
    }

    @GetMapping("verify")
    public String verify() {
        return "verify";
    }

}
