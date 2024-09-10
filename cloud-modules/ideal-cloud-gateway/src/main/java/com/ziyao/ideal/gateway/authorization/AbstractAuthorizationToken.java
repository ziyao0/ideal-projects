package com.ziyao.ideal.gateway.authorization;

import com.ziyao.ideal.security.core.SessionUser;

import java.util.Optional;

/**
 * @author ziyao zhang
 */
public abstract class AbstractAuthorizationToken implements Authorization {
    private String ip;
    private String resource;
    private String requestPath;
    private String domain;
    private SessionUser sessionUser;
    private boolean authorized;

    @Override
    public String getIp() {
        return this.ip;
    }

    @Override
    public String getResource() {
        return this.resource;
    }

    @Override
    public String getRequestPath() {
        return this.requestPath;
    }

    @Override
    public String getDomain() {
        return this.domain;
    }

    @Override
    public Optional<SessionUser> getPrincipal() {
        return Optional.ofNullable(this.sessionUser);
    }

    @Override
    public boolean isAuthorized() {
        return this.authorized;
    }

    @Override
    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public abstract static class AbstractBuilder {

        protected final AbstractAuthorizationToken authorizationToken;

        public AbstractBuilder(AbstractAuthorizationToken authorizationToken) {
            this.authorizationToken = authorizationToken;
        }

        public AbstractBuilder ip(String ip) {
            this.authorizationToken.ip = ip;
            return this;
        }

        public AbstractBuilder resource(String resource) {
            this.authorizationToken.resource = resource;
            return this;
        }

        public AbstractBuilder requestPath(String requestPath) {
            this.authorizationToken.requestPath = requestPath;
            return this;
        }

        public AbstractBuilder domain(String domain) {
            this.authorizationToken.domain = domain;
            return this;
        }

        public AbstractBuilder sessionUser(SessionUser sessionUser) {
            this.authorizationToken.sessionUser = sessionUser;
            return this;
        }

        public AbstractBuilder unauthorized() {
            this.authorizationToken.authorized = false;
            return this;
        }

        public AbstractBuilder authorized() {
            this.authorizationToken.authorized = true;
            return this;
        }

        public abstract AbstractAuthorizationToken build();
    }

}
