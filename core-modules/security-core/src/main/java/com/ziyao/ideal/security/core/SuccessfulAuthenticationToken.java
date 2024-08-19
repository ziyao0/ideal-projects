package com.ziyao.ideal.security.core;


import java.util.Collection;

/**
 * @author ziyao zhang
 */
public class SuccessfulAuthenticationToken extends AbstractAuthenticationToken {

    
    private static final long serialVersionUID = 919449188452752172L;

    private final UserInfo principal;

    public SuccessfulAuthenticationToken(UserInfo principal,
                                         Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

}
