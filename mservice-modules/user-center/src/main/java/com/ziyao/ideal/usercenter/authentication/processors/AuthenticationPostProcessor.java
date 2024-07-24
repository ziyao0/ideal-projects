package com.ziyao.ideal.usercenter.authentication.processors;

import com.ziyao.security.oauth2.core.Authentication;
import org.springframework.core.Ordered;

/**
 * @author ziyao zhang
 */
@FunctionalInterface
public interface AuthenticationPostProcessor extends Ordered {


    Authentication process(Authentication authentication);

    default int getOrder() {
        return 0;
    }
}
