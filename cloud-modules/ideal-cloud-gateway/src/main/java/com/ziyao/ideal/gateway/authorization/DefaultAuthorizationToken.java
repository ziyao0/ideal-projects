package com.ziyao.ideal.gateway.authorization;

import lombok.Getter;

/**
 * @author ziyao zhang
 */
@Getter
public class DefaultAuthorizationToken extends AbstractAuthorizationToken {


    public DefaultAuthorizationToken(final String token) {
        super(token);
    }

    public static Builder withAccessToken(final String token) {
        return new Builder(new DefaultAuthorizationToken(token));
    }

    public static class Builder extends AbstractBuilder<DefaultAuthorizationToken> {

        private final DefaultAuthorizationToken authorizationToken;

        public Builder(final DefaultAuthorizationToken authorizationToken) {
            super(authorizationToken);
            this.authorizationToken = authorizationToken;
        }

        @Override
        public DefaultAuthorizationToken build() {
            return authorizationToken;
        }
    }
}
