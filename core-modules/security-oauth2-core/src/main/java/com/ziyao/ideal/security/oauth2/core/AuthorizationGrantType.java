package com.ziyao.ideal.security.oauth2.core;

import java.util.Objects;

/**
 * @author ziyao zhang
 */
public record AuthorizationGrantType(String value) {

    public static final AuthorizationGrantType AUTHORIZATION_CODE = new AuthorizationGrantType("authorization_code");

    public static final AuthorizationGrantType REFRESH_TOKEN = new AuthorizationGrantType("refresh_token");

    public static final AuthorizationGrantType CLIENT_CREDENTIALS = new AuthorizationGrantType("client_credentials");

    public static final AuthorizationGrantType PASSWORD = new AuthorizationGrantType("password");

    public static final AuthorizationGrantType JWT_BEARER = new AuthorizationGrantType("urn:ietf:params:oauth:grant-type:jwt-bearer");

    public static final AuthorizationGrantType DEVICE_CODE = new AuthorizationGrantType("urn:ietf:params:oauth:grant-type:device_code");

    private static final AuthorizationGrantType OAUTH2_TYPE = new AuthorizationGrantType("oauth");

    private static final AuthorizationGrantType DEFAULT = new AuthorizationGrantType("default");

    public boolean matches(String code) {
        return Objects.equals(this.value, code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizationGrantType that = (AuthorizationGrantType) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
