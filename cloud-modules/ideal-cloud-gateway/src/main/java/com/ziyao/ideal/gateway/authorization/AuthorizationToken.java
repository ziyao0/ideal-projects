package com.ziyao.ideal.gateway.authorization;

import com.ziyao.ideal.gateway.core.ResponseDetails;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class AuthorizationToken extends AbstractAuthorizationToken {

    private ResponseDetails response = new ResponseDetails.Instance(401, "请求未认证");

    protected AuthorizationToken(String token) {
        super(token);
    }

    public static Builder form(Authorization authorization) {
        return (Builder) new Builder(new AuthorizationToken(authorization.getToken()))
                .ip(authorization.getIp())
                .resource(authorization.getResource())
                .domain(authorization.getDomain())
                .requestPath(authorization.getRequestPath())
                .sessionUser(authorization.getPrincipal().orElse(null));
    }

    public static class Builder extends AbstractBuilder<AuthorizationToken> {

        private final AuthorizationToken authorizationToken;

        public Builder(AuthorizationToken authorizationToken) {
            super(authorizationToken);
            this.authorizationToken = authorizationToken;
        }

        public Builder response(ResponseDetails response) {
            this.authorizationToken.response = response;
            return this;
        }

        public Builder response(HttpStatus httpStatus) {
            this.authorizationToken.response = ResponseDetails.of(httpStatus.value(), httpStatus.getReasonPhrase());
            return this;
        }

        @Override
        public AuthorizationToken build() {
            return authorizationToken;
        }
    }
}
