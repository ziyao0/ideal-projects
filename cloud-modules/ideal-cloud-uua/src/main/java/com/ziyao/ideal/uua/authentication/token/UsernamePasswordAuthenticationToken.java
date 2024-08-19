package com.ziyao.ideal.uua.authentication.token;

import com.ziyao.ideal.security.core.AbstractAuthenticationToken;
import com.ziyao.ideal.security.core.GrantedAuthority;
import org.springframework.data.keyvalue.annotation.KeySpace;


import java.util.Collection;

/**
 * @author ziyao zhang
 */
@KeySpace("security:authentication")
public class UsernamePasswordAuthenticationToken extends AbstractAuthenticationToken {

    
    private static final long serialVersionUID = 5517018814206006638L;

    private final Object principal;

    private Object credentials;

    public UsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    public UsernamePasswordAuthenticationToken(Object principal, Object credentials,
                                               Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(true);
    }

    public static UsernamePasswordAuthenticationToken unauthenticated(Object principal, Object credentials) {
        return new UsernamePasswordAuthenticationToken(principal, credentials);
    }

    public static UsernamePasswordAuthenticationToken authenticated(Object principal, Object credentials,
                                                                    Collection<? extends GrantedAuthority> authorities) {
        return new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
