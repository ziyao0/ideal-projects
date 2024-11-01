package com.ziyao.ideal.web.security.factory;

import com.ziyao.ideal.security.core.context.SecurityContext;
import com.ziyao.ideal.web.ResponseBuilder;
import com.ziyao.ideal.web.exception.ServiceException;
import com.ziyao.ideal.web.security.Secured;

import java.util.Objects;

/**
 * @author ziyao
 * @link <a href="https://github.com/ziyao0">ziyao for github</a>
 */
public class IdentSecureValidator implements SecureValidator {

    @Override
    public void validate(SecurityContext context, String condition) {

        Integer status = context.getPrincipal().getStatus();

        if (!Objects.equals(String.valueOf(status), condition)) {
            throw new ServiceException(ResponseBuilder.forbidden());
        }
    }

    @Override
    public Secured.Mode mode() {
        return Secured.Mode.IDENT;
    }
}
