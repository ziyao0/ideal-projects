package com.ziyao.ideal.gateway.security;

import com.auth0.jwt.interfaces.Claim;
import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.security.core.SessionUser;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ziyao zhang
 */
@Getter
public class AuthorizationSuccessful implements Authorization {

    @Serial
    private static final long serialVersionUID = 1906018666555325676L;
    private transient Map<String, Object> claims;
    private final String token;
    @Setter
    private String refreshToken;
    private boolean authorized;
    @Setter
    private SessionUser principal;

    public AuthorizationSuccessful(String token) {
        this.token = token;
    }

    public AuthorizationSuccessful(Map<String, ?> claims, String token) {
        if (Collections.isEmpty(claims)) {
            this.claims = new HashMap<>();
        } else {
            Map<String, Object> finalClaims = new HashMap<>(claims.size());
            for (Map.Entry<String, ?> entry : claims.entrySet()) {
                if (entry.getValue() instanceof Claim claim) {
                    finalClaims.put(entry.getKey(), claim.as(Object.class));
                } else {
                    finalClaims.put(entry.getKey(), entry.getValue());
                }
            }
            this.claims = finalClaims;
        }
        this.token = token;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public String getRefreshToken() {
        return this.refreshToken;
    }

    @Override
    public boolean isAuthorized() {
        return this.authorized;
    }

    @Override
    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public static class Builder {
        private String token;
        private String refreshToken;
        private boolean authorized;
        private SessionUser principal;
        private transient Map<String, Object> claims;

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public Builder authorized(boolean authorized) {
            this.authorized = authorized;
            return this;
        }

        public Builder principal(SessionUser principal) {
            this.principal = principal;
            return this;
        }

        public Builder claims(Map<String, Object> claims) {
            this.claims = claims;
            return this;
        }

        public AuthorizationSuccessful build() {
            AuthorizationSuccessful authorization = new AuthorizationSuccessful(claims, token);
            authorization.setAuthorized(authorized);
            authorization.setRefreshToken(refreshToken);
            authorization.setPrincipal(principal);
            return authorization;
        }
    }

}
