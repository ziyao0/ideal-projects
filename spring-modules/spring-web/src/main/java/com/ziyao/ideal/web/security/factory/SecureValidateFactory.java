package com.ziyao.ideal.web.security.factory;

import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.security.core.context.SecurityContextHolder;
import com.ziyao.ideal.web.ResponseBuilder;
import com.ziyao.ideal.web.exception.ServiceException;
import com.ziyao.ideal.web.security.Secured;

import java.util.List;

/**
 * @author ziyao
 * @link <a href="https://github.com/ziyao0">ziyao for github</a>
 */
public record SecureValidateFactory(List<SecureValidator> validators) {


    public SecureValidateFactory {
        Assert.notNull(validators, "validators must not be null");
    }

    public void validate(Secured.Mode mode, String condition) {

        if (SecurityContextHolder.unauthorized()) {
            throw new ServiceException(ResponseBuilder.forbidden());
        }

        for (SecureValidator validator : validators) {
            if (validator.mode().equals(mode)) {
                validator.validate(SecurityContextHolder.getContext(), condition);
            }
        }
    }
}
