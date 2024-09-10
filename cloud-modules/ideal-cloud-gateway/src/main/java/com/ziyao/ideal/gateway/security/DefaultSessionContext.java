package com.ziyao.ideal.gateway.security;

import com.ziyao.ideal.security.core.SessionUser;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

/**
 * @author ziyao
 */
@Data
public class DefaultSessionContext implements SessionContext, Serializable {

    @Serial
    private static final long serialVersionUID = -3447048378569999827L;

    private String ip;
    private String sessionId;
    private String resourceUri;
    private String requestUri;
    private String timestamp;
    private String digest;
    private String token;
    private String refreshToken;
    private String domain;
    private SessionUser sessionUser;


    public Optional<SessionUser> getSessionUser() {
        return Optional.ofNullable(sessionUser);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final DefaultSessionContext sessionContext;

        public Builder() {
            this.sessionContext = new DefaultSessionContext();
        }

        private Builder domain(String domain) {
            this.sessionContext.domain = domain;
            return this;
        }

        public Builder ip(String ip) {
            this.sessionContext.ip = ip;
            return this;
        }

        public Builder requestUri(String requestUri) {
            this.sessionContext.requestUri = requestUri;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.sessionContext.timestamp = timestamp;
            return this;
        }

        public Builder digest(String digest) {
            this.sessionContext.digest = digest;
            return this;
        }

        public Builder token(String token) {
            this.sessionContext.token = token;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.sessionContext.refreshToken = refreshToken;
            return this;
        }

        public Builder resourceUri(String resourceUri) {
            this.sessionContext.resourceUri = resourceUri;
            return this;
        }

        public Builder sessionId(String sessionId) {
            this.sessionContext.sessionId = sessionId;
            return this;
        }

        public Builder sessionUser(SessionUser sessionUser) {
            this.sessionContext.sessionUser = sessionUser;
            return this;
        }

        public DefaultSessionContext build() {
            return this.sessionContext;
        }
    }
}
