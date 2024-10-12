package com.ziyao.ideal.security.core;

import com.ziyao.ideal.core.Strings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class DefaultAuthenticationToken extends AbstractAuthenticationToken {


    private final User principal;

    /**
     * 使用提供的颁发机构数组创建令牌。
     *
     * @param authorities 此身份验证对象表示的主体的 GrantedAuthority 集合。
     */
    public DefaultAuthenticationToken(User principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.setAuthenticated(Strings.hasText(principal.getUsername()));
    }

    @Override
    public User getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private User principal;
        private List<GrantedAuthority> authorities = new ArrayList<>();
        private boolean authenticated = false;

        public Builder principal(User principal) {
            this.principal = principal;
            return this;
        }

        public Builder authenticated() {
            this.authenticated = true;
            return this;
        }

        public Builder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList<>(authorities);
            return this;
        }

        public Builder authorities(Consumer<Collection<GrantedAuthority>> authoritiesConsumer) {
            authoritiesConsumer.accept(this.authorities);
            return this;
        }

        public DefaultAuthenticationToken build() {
            DefaultAuthenticationToken authenticationToken = new DefaultAuthenticationToken(principal, authorities);
            authenticationToken.setAuthenticated(authenticated);
            return authenticationToken;
        }

    }
}
