package com.ziyao.ideal.gateway.authorization;

import lombok.Getter;

/**
 * @author ziyao zhang
 */
@Getter
public class SimpleAuthorizationToken extends AbstractAuthorizationToken {

    private final String sessionId;

    public SimpleAuthorizationToken(final String sessionId) {
        this.sessionId = sessionId;
    }

    public static Builder withSessionId(final String sessionId) {
        return new Builder(sessionId);
    }

    public static class Builder extends AbstractBuilder {


        public Builder(final String sessionId) {
            super(new SimpleAuthorizationToken(sessionId));
        }

        @Override
        public AbstractAuthorizationToken build() {
            return authorizationToken;
        }
    }
}
