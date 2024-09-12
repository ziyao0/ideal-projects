package com.ziyao.ideal.gateway.authorization;

import lombok.Getter;

/**
 * @author ziyao zhang
 */
@Getter
public class DefaultAuthorizationToken extends AbstractAuthorizationToken {

    private final String sessionId;

    public DefaultAuthorizationToken(final String sessionId) {
        this.sessionId = sessionId;
    }

    public static Builder withSessionId(final String sessionId) {
        return new Builder(sessionId);
    }

    public static class Builder extends AbstractBuilder {


        public Builder(final String sessionId) {
            super(new DefaultAuthorizationToken(sessionId));
        }

        @Override
        public AbstractAuthorizationToken build() {
            return authorizationToken;
        }
    }
}
