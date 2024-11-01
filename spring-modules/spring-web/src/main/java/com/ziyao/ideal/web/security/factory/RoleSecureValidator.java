package com.ziyao.ideal.web.security.factory;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.core.GrantedAuthority;
import com.ziyao.ideal.security.core.context.SecurityContext;
import com.ziyao.ideal.web.ResponseBuilder;
import com.ziyao.ideal.web.exception.ServiceException;
import com.ziyao.ideal.web.security.Secured;

/**
 * @author ziyao
 * @link <a href="https://github.com/ziyao0">ziyao for github</a>
 */
public class RoleSecureValidator implements SecureValidator {


    @Override
    public void validate(SecurityContext context, String condition) {
        // 匹配是否有操作权限
        for (GrantedAuthority authority : context.getAuthorities()) {
            if (Strings.equalsIgnoreCase(condition, authority.getAuthority())) {
                return;
            }
        }
        throw new ServiceException(ResponseBuilder.forbidden());
    }

    @Override
    public Secured.Mode mode() {
        return Secured.Mode.ROLE;
    }
}
