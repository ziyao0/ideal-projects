package com.ziyao.ideal.uua.service.security;

import com.ziyao.ideal.uua.authentication.AuthenticationHandler;
import com.ziyao.ideal.uua.authentication.AuthenticationManager;
import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.AuthenticationUtils;
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
