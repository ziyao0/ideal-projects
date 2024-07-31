package com.ziyao.ideal.uua.authentication.strategy;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Component
@RequiredArgsConstructor
public class BusinessLogicFailureStrategy implements AuthenticationFailureStrategy {

    @Override
    public Authentication handleFailure(Authentication authentication, Exception exception) {
        return null;
    }

    @Override
    public boolean isSupport(Class<? extends Exception> exceptionClass) {
        return ServiceException.class.isAssignableFrom(exceptionClass);
    }
}
