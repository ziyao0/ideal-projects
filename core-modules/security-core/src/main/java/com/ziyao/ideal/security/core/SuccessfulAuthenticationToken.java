package com.ziyao.ideal.security.core;

import java.io.Serial;
import java.util.Collection;

/**
 * @author ziyao zhang
 */
public class SuccessfulAuthenticationToken extends AbstractAuthenticationToken {

    @Serial
    private static final long serialVersionUID = 919449188452752172L;

    private final User user;

    public SuccessfulAuthenticationToken(User user,
                                         Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.user = user;
    }

    @Override
    public Object getPrincipal() {
        return this.user;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

}
