package com.ziyao.ideal.uaa.authentication.token;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.GrantedAuthority;
import com.ziyao.ideal.web.response.ResponseDetails;
import lombok.Getter;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class FailureAuthenticationToken implements Authentication {

    @Serial
    private static final long serialVersionUID = -9065289292026217351L;

    private final Integer status;

    private final String message;

    private final Object data;

    private FailureAuthenticationToken(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public FailureAuthenticationToken(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {

    }

    public static FailureAuthenticationToken of(Integer status, String message) {
        return new FailureAuthenticationToken(status, message);
    }

    public static FailureAuthenticationToken of(Integer status, String message, Object data) {
        return new FailureAuthenticationToken(status, message, data);
    }

    public static FailureAuthenticationToken of(ResponseDetails responseDetails) {
        return of(responseDetails.getStatus(), responseDetails.getMessage());
    }
}
