package com.ziyao.ideal.usercenter.authentication.converter;

import com.ziyao.ideal.usercenter.request.AuthenticationRequest;
import com.ziyao.ideal.security.core.Authentication;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * @author ziyao zhang
 */
public class DelegatingAuthenticationConverter implements AuthenticationConverter {


    private final List<AuthenticationConverter> authenticationConverters;

    public DelegatingAuthenticationConverter(List<AuthenticationConverter> authenticationConverters) {
        this.authenticationConverters = authenticationConverters;
    }

    @Override
    public Authentication convert(AuthenticationRequest request) {
        for (AuthenticationConverter authenticationConverter : this.authenticationConverters) {
            Authentication authentication = authenticationConverter.convert(request);
            if (authentication != null) {
                return authentication;
            }
        }
        return null;
    }

    @Override
    public Authentication convert(HttpServletRequest request) {
        for (AuthenticationConverter authenticationConverter : this.authenticationConverters) {
            Authentication authentication = authenticationConverter.convert(request);
            if (authentication != null) {
                return authentication;
            }
        }
        return null;
    }
}
