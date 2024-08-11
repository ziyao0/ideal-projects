package com.ziyao.ideal.gateway.authorization.token;

import lombok.Data;

import java.io.Serial;

/**
 * @author ziyao zhang
 */
@Data
public class FailureAuthorization implements com.ziyao.ideal.gateway.authorization.token.Authorization {

    @Serial
    private static final long serialVersionUID = 8104427680804234299L;

    public FailureAuthorization() {
    }

    public FailureAuthorization(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public String getToken() {
        return null;
    }
}
