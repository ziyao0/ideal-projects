package com.ziyao.ideal.uua.authentication.converter;

import com.ziyao.ideal.uua.request.AuthenticationRequest;
import com.ziyao.ideal.security.core.Authentication;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author ziyao
 */
public interface AuthenticationConverter {


    Authentication convert(AuthenticationRequest request);

    Authentication convert(HttpServletRequest request);
}
