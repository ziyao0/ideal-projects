package com.ziyao.ideal.gateway.authorization;

import com.ziyao.ideal.security.core.User;
import lombok.Setter;

import java.util.Optional;

/**
 * @author ziyao zhang
 */
@Setter
public abstract class AbstractAuthorizationToken implements Authorization {

    private final String token;
    private String ip;
    private String resource;
    private String requestPath;
    private String domain;
    private User user;
    private boolean authorized;

    protected AbstractAuthorizationToken(String token) {
        this.token = token;
    }

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
    public String getToken() {
        return this.token;
    }

    public Optional<User> getPrincipal() {
        return Optional.ofNullable(this.user);
    }

    @Override
    public boolean isAuthorized() {
        return this.authorized;
    }

    @Override
    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public abstract static class AbstractBuilder<T extends AbstractAuthorizationToken> {

        protected final T authorizationToken;

        public AbstractBuilder(T authorizationToken) {
            this.authorizationToken = authorizationToken;
        }

        public AbstractBuilder<T> ip(String ip) {
            this.authorizationToken.setIp(ip);
            return this;
        }

        public AbstractBuilder<T> resource(String resource) {
            this.authorizationToken.setResource(resource);
            return this;
        }

        public AbstractBuilder<T> requestPath(String requestPath) {
            this.authorizationToken.setRequestPath(requestPath);
            return this;
        }

        public AbstractBuilder<T> domain(String domain) {
            this.authorizationToken.setDomain(domain);
            return this;
        }

        public AbstractBuilder<T> user(User user) {
            this.authorizationToken.setUser(user);
            return this;
        }

        public AbstractBuilder<T> unauthorized() {
            this.authorizationToken.setAuthorized(false);
            return this;
        }

        public AbstractBuilder<T> authorized() {
            this.authorizationToken.setAuthorized(true);
            return this;
        }

        public abstract T build();
    }

}
