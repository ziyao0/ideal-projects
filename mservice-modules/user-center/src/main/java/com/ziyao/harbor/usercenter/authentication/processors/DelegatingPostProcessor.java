package com.ziyao.harbor.usercenter.authentication.processors;

import com.ziyao.eis.core.Assert;
import com.ziyao.security.oauth2.core.Authentication;

import java.util.Comparator;
import java.util.List;

/**
 * @author ziyao zhang
 */

public class DelegatingPostProcessor implements AuthenticationPostProcessor {

    private final List<AuthenticationPostProcessor> delegates;

    public DelegatingPostProcessor(List<AuthenticationPostProcessor> delegates) {
        Assert.notNull(delegates, "认证后置处理器不能为空");
        delegates.sort(Comparator.comparingInt(AuthenticationPostProcessor::getOrder));
        this.delegates = List.copyOf(delegates);
    }

    @Override
    public Authentication process(Authentication authentication) {

        for (AuthenticationPostProcessor processor : this.delegates) {
            authentication = processor.process(authentication);
        }
        return authentication;
    }
}
