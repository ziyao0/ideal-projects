package com.ziyao.ideal.uua.authentication.processors;

import com.ziyao.ideal.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author ziyao zhang
 */
@Component
public class AuthorizationRecordPostProcessor implements AuthenticationPostProcessor {

    @Override
    public Authentication process(Authentication authentication) {

        // 记录授权记录 、登录记录等

        return null;
    }
}
