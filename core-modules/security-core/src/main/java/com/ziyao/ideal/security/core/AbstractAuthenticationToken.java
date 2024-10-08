package com.ziyao.ideal.security.core;

import com.ziyao.ideal.core.Strings;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public abstract class AbstractAuthenticationToken implements Authentication, CredentialsContainer {

    private final Collection<? extends GrantedAuthority> authorities;

    private boolean authenticated;

    /**
     * 使用提供的颁发机构数组创建令牌。
     *
     * @param authorities 此身份验证对象表示的主体的 GrantedAuthority 集合。
     */
    public AbstractAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        if (authorities == null) {
            this.authorities = Set.of();
            return;
        }
        Collection<GrantedAuthority> f = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            if (authority != null) f.add(authority);
        }
        this.authorities = Collections.unmodifiableCollection(f);

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }


    public String getName() {
        if (getPrincipal() == null) {
            return Strings.EMPTY;
        }
        if (getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        return getPrincipal().toString();
    }

    @Override
    public void eraseCredentials() {
        eraseSecret(getCredentials());
        eraseSecret(getPrincipal());
    }

    private void eraseSecret(Object secret) {
        if (secret instanceof CredentialsContainer container) {
            container.eraseCredentials();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractAuthenticationToken test)) {
            return false;
        }
        if (!this.authorities.equals(test.authorities)) {
            return false;
        }


        if ((this.getCredentials() == null) && (test.getCredentials() != null)) {
            return false;
        }
        if ((this.getCredentials() != null) && !this.getCredentials().equals(test.getCredentials())) {
            return false;
        }
        if (this.getPrincipal() == null && test.getPrincipal() != null) {
            return false;
        }
        if (this.getPrincipal() != null && !this.getPrincipal().equals(test.getPrincipal())) {
            return false;
        }
        return this.isAuthenticated() == test.isAuthenticated();
    }


    @Override
    public int hashCode() {
        int code = 31;
        for (GrantedAuthority authority : this.authorities) {
            code ^= authority.hashCode();
        }
        if (this.getPrincipal() != null) {
            code ^= this.getPrincipal().hashCode();
        }
        if (this.getCredentials() != null) {
            code ^= this.getCredentials().hashCode();
        }

        if (this.isAuthenticated()) {
            code ^= -37;
        }
        return code;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" +
                "Principal=" + getPrincipal() + ", " +
                "Credentials=[PROTECTED], " +
                "Authenticated=" + isAuthenticated() + ", " +
                "Granted Authorities=" + this.authorities +
                "]";
    }

}
