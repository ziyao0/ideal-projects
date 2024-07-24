package com.ziyao.ideal.usercenter.service.security;

import com.ziyao.ideal.usercenter.authentication.AuthenticationHandler;
import com.ziyao.ideal.usercenter.authentication.AuthenticationManager;
import com.ziyao.security.oauth2.core.Authentication;
import com.ziyao.security.oauth2.core.support.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ziyao zhang
 */
@Service
@RequiredArgsConstructor
public class DefaultAuthenticationService implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationHandler authenticationHandler;

    @Override
    public Authentication login(Authentication authentication) {

        Authentication authenticated = authentication;
        try {
            authenticated = authenticationManager.authenticate(authentication);

            if (AuthenticationUtils.isAuthenticated(authenticated)) {
                return authenticationHandler.onSuccessful(authenticated);
            } else {
                return authenticationHandler.onFailure(authenticated, null);
            }
        } catch (Exception ex) {
            return authenticationHandler.onFailure(authenticated, ex);
        }
    }
}
