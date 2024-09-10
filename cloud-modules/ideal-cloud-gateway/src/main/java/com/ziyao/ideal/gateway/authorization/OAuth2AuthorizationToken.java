package com.ziyao.ideal.gateway.authorization;

import lombok.Getter;

/**
 * @author ziyao zhang
 */
@Getter
public class OAuth2AuthorizationToken extends AbstractAuthorizationToken {

    private final String accessToken;
    private String refreshToken;
    private long timestamp;
    private String digest;

    public OAuth2AuthorizationToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public static Builder withAccessToken(final String accessToken) {
        return new Builder(new OAuth2AuthorizationToken(accessToken));
    }

    public static class Builder extends AbstractBuilder {

        private final OAuth2AuthorizationToken authorizationToken;

        public Builder(OAuth2AuthorizationToken authorizationToken) {
            super(authorizationToken);
            this.authorizationToken = authorizationToken;
        }

        public Builder refreshToken(final String refreshToken) {
            this.authorizationToken.refreshToken = refreshToken;
            return this;
        }

        public Builder timestamp(final long timestamp) {
            this.authorizationToken.timestamp = timestamp;
            return this;
        }

        public Builder digest(final String digest) {
            this.authorizationToken.digest = digest;
            return this;
        }

        @Override
        public AbstractAuthorizationToken build() {
            return authorizationToken;
        }
    }
}
