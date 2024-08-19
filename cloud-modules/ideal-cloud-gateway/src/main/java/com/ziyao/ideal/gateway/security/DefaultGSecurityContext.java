package com.ziyao.ideal.gateway.security;

import lombok.Data;


import java.io.Serializable;

/**
 * @author ziyao
 */
@Data
public class DefaultGSecurityContext implements GSecurityContext, Serializable {
    
    private static final long serialVersionUID = -3447048378569999827L;

    private String ip;
    private String resourceUri;
    private String requestUri;
    private String timestamp;
    private String digest;
    private String token;
    private String refreshToken;
    private String name;
    private String domain;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String ip;
        private String resourceUri;
        private String requestUri;
        private String timestamp;
        private String digest;
        private String token;
        private String refreshToken;
        private String name;
        private String domain;

        private Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder requestUri(String requestUri) {
            this.requestUri = requestUri;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder digest(String digest) {
            this.digest = digest;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public Builder resourceUri(String resourceUri) {
            this.resourceUri = resourceUri;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public DefaultGSecurityContext build() {
            DefaultGSecurityContext context = new DefaultGSecurityContext();
            context.setToken(token);
            context.setRequestUri(requestUri);
            context.setIp(ip);
            context.setRefreshToken(refreshToken);
            context.setTimestamp(timestamp);
            context.setDigest(digest);
            context.setResourceUri(resourceUri);
            context.setName(name);
            context.setDomain(domain);
            return context;
        }
    }
}
