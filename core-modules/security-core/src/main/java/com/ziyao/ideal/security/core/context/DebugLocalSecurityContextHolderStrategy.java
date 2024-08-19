package com.ziyao.ideal.security.core.context;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.GrantedAuthority;
import com.ziyao.ideal.security.core.UserDetails;
import org.springframework.util.function.SingletonSupplier;


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
        SecurityContext context = createEmptyContext();
        context.setAuthentication(createAuthenticatedToken());
        return new SupplierDeferredSecurityContext(SingletonSupplier.of(context), SecurityContextHolder.getContextHolderStrategy());
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
                return Lists.newArrayList();
            }
        };

        return new Authentication() {
            
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
                return Lists.newArrayList();
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
