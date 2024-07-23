package com.ziyao.harbor.usercenter.authentication.processors;

import com.ziyao.security.oauth2.core.Authentication;
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
