package com.ziyao.ideal.usercenter.authentication.core;

import com.ziyao.ideal.security.core.UserDetails;
import lombok.Data;

import java.util.Set;

/**
 * @author ziyao zhang
 */
@Data
public class AuthenticatedUser {

    private UserDetails user;

    private Set<?> resources;

    private boolean authenticated;

    private String token;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private UserDetails user;

        public Builder user(UserDetails user) {
            this.user = user;
            return this;
        }

        public AuthenticatedUser build() {
            AuthenticatedUser authenticatedUser = new AuthenticatedUser();
            authenticatedUser.setUser(this.user);
            authenticatedUser.setAuthenticated(true);
            return authenticatedUser;
        }
    }

}
