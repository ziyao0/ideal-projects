package com.ziyao.ideal.uaa.authentication.processors;

import com.ziyao.ideal.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author ziyao zhang
 */
@Component
public class TokenGenerationPostProcessor implements AuthenticationPostProcessor {

    @Override
    public Authentication process(Authentication authentication) {

        //生成认证token
        // 判断是否需要刷新token

        return null;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
