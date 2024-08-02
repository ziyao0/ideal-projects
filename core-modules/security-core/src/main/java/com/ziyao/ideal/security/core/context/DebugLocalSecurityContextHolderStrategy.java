package com.ziyao.ideal.security.core.context;

import com.ziyao.ideal.security.core.GrantedAuthority;
import com.ziyao.ideal.security.core.UserDetails;
import com.ziyao.ideal.security.core.Authentication;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

/**
 * @author ziyao zhang
 */
public class DebugLocalSecurityContextHolderStrategy implements SecurityContextHolderStrategy {

    @Override
    public void clearContext() {

    }

    @Override
    public SecurityContext getContext() {
        return getDeferredContext().get();
    }

    @Override
    public DeferredSecurityContext getDeferredContext() {
        return () -> {
            SecurityContext context = createEmptyContext();
            context.setAuthentication(createAuthenticatedToken());
            return context;
        };
    }

    @Override
    public void setContext(SecurityContext context) {

    }

    @Override
    public SecurityContext createEmptyContext() {
        return new SecurityContextImpl();
    }

    private Authentication createAuthenticatedToken() {

        UserDetails userDetails = new UserDetails() {
            @Serial
            private static final long serialVersionUID = 3445961897468761978L;

            @Override
            public String getUsername() {
                return "admin";
            }

            @Override
            public String getPassword() {
                return "";
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }
        };

        return new Authentication() {
            @Serial
            private static final long serialVersionUID = 7615063167518644592L;

            @Override
            public Object getPrincipal() {
                return userDetails;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean authenticated) {

            }
        };
    }
}
