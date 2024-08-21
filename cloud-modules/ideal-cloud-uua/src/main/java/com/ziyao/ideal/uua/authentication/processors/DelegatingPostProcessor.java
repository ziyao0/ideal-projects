package com.ziyao.ideal.uua.authentication.processors;

import com.google.common.collect.Lists;
import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.security.core.Authentication;

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
        this.delegates = Lists.newArrayList(delegates);
    }

    @Override
    public Authentication process(Authentication authentication) {

        for (AuthenticationPostProcessor processor : this.delegates) {
            authentication = processor.process(authentication);
        }
        return authentication;
    }
}
