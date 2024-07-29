package com.ziyao.ideal.security.core.context;

import java.util.function.Supplier;

/**
 * @author ziyao zhang
 */
public interface SecurityContextHolderStrategy {

    /**
     * 清理当前上下文
     */
    void clearContext();


    SecurityContext getContext();


    default Supplier<SecurityContext> getDeferredContext() {
        return this::getContext;
    }


    void setContext(SecurityContext context);


    default void setDeferredContext(Supplier<SecurityContext> deferredContext) {
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
