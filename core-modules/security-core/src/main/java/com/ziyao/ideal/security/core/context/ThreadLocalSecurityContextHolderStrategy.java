package com.ziyao.ideal.security.core.context;

import com.ziyao.ideal.core.Assert;
import org.springframework.util.function.SingletonSupplier;

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
            result = new SupplierDeferredSecurityContext(SingletonSupplier.of(context),
                    SecurityContextHolder.getContextHolderStrategy());
            contextHolder.set(result);
        }
        return result;
    }

    @Override
    public void setContext(SecurityContext context) {
        contextHolder.set(
                new SupplierDeferredSecurityContext(SingletonSupplier.of(context),
                        SecurityContextHolder.getContextHolderStrategy()));
    }

    @Override
    public void setDeferredContext(DeferredSecurityContext deferredContext) {

        SecurityContext securityContext = deferredContext.get();

        Assert.notNull(securityContext, "传入的请求上下文不能为空");

        contextHolder.set(deferredContext);
    }

    @Override
    public SecurityContext createEmptyContext() {
        return new SecurityContextImpl();
    }
}
