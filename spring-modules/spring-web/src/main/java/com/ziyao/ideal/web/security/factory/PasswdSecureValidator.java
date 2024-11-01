package com.ziyao.ideal.web.security.factory;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.core.User;
import com.ziyao.ideal.security.core.context.SecurityContext;
import com.ziyao.ideal.web.ResponseBuilder;
import com.ziyao.ideal.web.exception.ServiceException;
import com.ziyao.ideal.web.security.Secured;

/**
 * @author ziyao
 * @link <a href="https://github.com/ziyao0">ziyao for github</a>
 */
public abstract class PasswdSecureValidator implements SecureValidator {

    @Override
    public void validate(SecurityContext context, String condition) {
        User principal = context.getPrincipal();
        if (principal == null || Strings.hasText(principal.getUsername())) {
            throw new ServiceException(ResponseBuilder.unauthorized());
        }
        doValidate(principal.getUsername(), principal.getPassword());
        // 擦除密码
        principal.eraseCredentials();
    }

    /**
     * 待扩展的密码校验
     *
     * @param username 用户账号
     */
    protected abstract void doValidate(String username, String password);

    @Override
    public Secured.Mode mode() {
        return Secured.Mode.PASSWD;
    }
}
