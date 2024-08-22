package com.ziyao.ideal.uaa.authentication.support;

import com.ziyao.ideal.uaa.common.exception.AuthenticationFailureException;
import com.ziyao.ideal.uaa.common.exception.Errors;
import com.ziyao.ideal.security.core.UserDetails;

/**
 * @author zhangziyao
 */
public abstract class UserDetailsValidator {

    /**
     * 检查账号信息是否为空
     *
     * @param check 待检测对象
     */
    public static void assertExists(UserDetails check) {
        if (check == null) {
            throw new AuthenticationFailureException(Errors.ERROR_100005);
        }
    }

    /**
     * 检查用户
     *
     * @param check the UserDetails instance whose status should be checked.
     * @throws com.ziyao.ideal.web.exception.ServiceException 检查未通过抛出异常
     */
    public static void validated(UserDetails check) {
        if (!check.isAccountNonLocked()) {
            throw new AuthenticationFailureException(Errors.ERROR_100001);
        }
        if (!check.isEnabled()) {
            throw new AuthenticationFailureException(Errors.ERROR_100002);
        }
        if (!check.isAccountNonExpired()) {
            throw new AuthenticationFailureException(Errors.ERROR_100003);
        }
        if (!check.isCredentialsNonExpired()) {
            throw new AuthenticationFailureException(Errors.ERROR_100004);
        }
    }

    public UserDetailsValidator() {
    }
}
