package com.ziyao.ideal.gateway.security;

import lombok.Data;



/**
 * @author ziyao zhang
 */
@Data
public class AuthorizationFailure implements Authorization {

    
    private static final long serialVersionUID = -3210692430079170373L;

    private String token;
    private String refreshToken;

    private int status;
    private String message;
    private Object data;


    public AuthorizationFailure(String token, String refreshToken, int status, String message, Object data) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public AuthorizationFailure(String token, String refreshToken, int status, String message) {
        this(token, refreshToken, status, message, null);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String getToken() {
        return "";
    }

    @Override
    public String getRefreshToken() {
        return "";
    }

    @Override
    public boolean isAuthorized() {
        return false;
    }

    @Override
    public void setAuthorized(boolean authorized) {

    }

    public static class Builder {
        private String token;
        private String refreshToken;
        private int status;
        private String message;
        private Object data;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public AuthorizationFailure build() {
            return new AuthorizationFailure(token, refreshToken, status, message, data);
        }
    }
}
