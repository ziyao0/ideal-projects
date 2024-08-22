package com.ziyao.ideal.uaa.authentication.converter;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.uaa.request.AuthenticationRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ziyao
 */
public interface AuthenticationConverter {

    /**
     * 转换请求为后认证对象
     *
     * @param request 请求参数
     * @return {@link Authentication}
     */
    Authentication convert(AuthenticationRequest request);

    /**
     * 重请求中获取认证对象
     *
     * @param request 请求对象 {@link HttpServletRequest}
     * @return {@link Authentication}
     */
    Authentication convert(HttpServletRequest request);
}
