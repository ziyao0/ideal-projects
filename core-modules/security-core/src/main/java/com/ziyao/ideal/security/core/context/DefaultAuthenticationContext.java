package com.ziyao.ideal.security.core.context;

import com.ziyao.ideal.security.core.Authentication;

import java.io.Serial;

/**
 * @author ziyao zhang
 */
public class DefaultAuthenticationContext implements AuthenticationContext {
    @Serial
    private static final long serialVersionUID = -6481012224001521435L;

    private Authentication authentication;

    @Override
    public Authentication getAuthentication() {
        return this.authentication;
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
}
