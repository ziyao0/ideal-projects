package com.ziyao.ideal.usercenter.common.exception;

import lombok.Data;

/**
 * @author ziyao
 */
@Data
public abstract class AuthenticationExceptions {


    /**
     * 创建验证密码失败异常
     */
    public static AuthenticationFailureException createValidatedFailure() {
        throw new AuthenticationFailureException(Errors.ERROR_100006);
    }

    private AuthenticationExceptions() {
        throw new IllegalStateException();
    }
}
