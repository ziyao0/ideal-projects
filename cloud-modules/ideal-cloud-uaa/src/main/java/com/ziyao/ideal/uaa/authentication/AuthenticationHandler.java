package com.ziyao.ideal.uaa.authentication;

import com.ziyao.ideal.security.core.Authentication;

/**
 * 认证处理起
 *
 * @author ziyao zhang
 */
public interface AuthenticationHandler {

    /**
     * 认证成功后执行该方法
     *
     * @param authentication 认证对象
     * @return 返回认证成功的认证对象
     */
    Authentication onSuccessful(Authentication authentication);

    /**
     * 认证失败后执行
     *
     * @param authentication 认证对象
     * @param ex             异常信息
     * @return 返回需要响应给前端的认证对象
     */
    Authentication onFailure(Authentication authentication, Exception ex);

}
