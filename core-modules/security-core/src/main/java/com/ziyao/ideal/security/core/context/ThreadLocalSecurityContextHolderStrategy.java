package com.ziyao.ideal.security.core.context;

import com.ziyao.ideal.core.Assert;

/**
 * @author ziyao zhang
 */
public class ThreadLocalSecurityContextHolderStrategy implements SecurityContextHolderStrategy {

    private static final ThreadLocal<DeferredSecurityContext> contextHolder = new ThreadLocal<>();


    @Override
    public void clearContext() {
        contextHolder.remove();
    }

    @Override
    public SecurityContext getContext() {
        return getDeferredContext().get();
    }

    @Override
    public DeferredSecurityContext getDeferredContext() {
        DeferredSecurityContext result = contextHolder.get();
        if (result == null) {
            SecurityContext context = createEmptyContext();
            result = () -> context;
            contextHolder.set(result);
        }
        return result;
    }

    @Override
    public void setContext(SecurityContext context) {
        contextHolder.set(() -> context);
    }

    @Override
    public void setDeferredContext(DeferredSecurityContext deferredContext) {
        DeferredSecurityContext notNullDeferredContext = () -> {
            SecurityContext securityContext = deferredContext.get();
            Assert.notNull(securityContext, "传入的请求上下文不能为空");
            return securityContext;
        };
        contextHolder.set(notNullDeferredContext);
    }

    @Override
    public SecurityContext createEmptyContext() {
        return new SecurityContextImpl();
    }
}
