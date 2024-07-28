package com.ziyao.ideal.uua.authentication.processors;

import com.ziyao.ideal.security.core.Authentication;
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
