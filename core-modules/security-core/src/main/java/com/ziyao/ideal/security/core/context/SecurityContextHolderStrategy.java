package com.ziyao.ideal.security.core.context;

import org.springframework.util.function.SingletonSupplier;

/**
 * @author ziyao zhang
 */
public interface SecurityContextHolderStrategy {

    /**
     * 清理当前上下文
     */
    void clearContext();


    SecurityContext getContext();


    default DeferredSecurityContext getDeferredContext() {
        return new SupplierDeferredSecurityContext(SingletonSupplier.of(this::getContext), SecurityContextHolder.getContextHolderStrategy());
    }


    void setContext(SecurityContext context);


    default void setDeferredContext(DeferredSecurityContext deferredContext) {
        setContext(deferredContext.get());
    }

    SecurityContext createEmptyContext();

    default boolean isAuthentication() {
        if (getContext() != null) {
            return getContext().getAuthentication().isAuthenticated();
        } else {
            return false;
        }
    }
}
