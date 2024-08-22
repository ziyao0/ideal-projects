package com.ziyao.ideal.uaa.authentication.strategy;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.uaa.authentication.token.FailureAuthenticationToken;
import com.ziyao.ideal.web.response.ResponseDetails;

/**
 * @author ziyao zhang
 */
public interface AuthenticationFailureStrategy {

    /**
     * 处理认证失败逻辑
     * <p>
     * 执行改方法前必须先执行{@link #isSupport(Class)}进行验证
     * 如果{@link #isSupport(Class)}返回了false则说明当前类不支持传入的异常处理逻辑
     *
     * @param authentication 认证上下文
     * @param exception      待处理异常信息
     * @return 返回要响应的认证信息
     */
    Authentication handleFailure(Authentication authentication, Exception exception);

    boolean isSupport(Class<? extends Exception> exceptionClass);

    default Authentication createFailureAuthentication(Integer status, String message) {
        return FailureAuthenticationToken.of(status, message);
    }

    default Authentication createFailureAuthentication(ResponseDetails responseDetails) {
        return FailureAuthenticationToken.of(responseDetails);
    }

    default Authentication createFailureAuthentication(ResponseDetails sm, Object data) {
        return FailureAuthenticationToken.of(sm.getStatus(), sm.getMessage(), data);
    }

    default Authentication createFailureAuthentication(int status, String message, Object data) {
        return FailureAuthenticationToken.of(status, message, data);
    }

}
