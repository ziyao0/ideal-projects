package com.ziyao.ideal.security.core.context;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.AuthenticationUtils;
import org.springframework.util.function.SingletonSupplier;

/**
 * @author ziyao zhang
 */
public interface SecurityContextHolderStrategy {

    /**
     * 清理当前上下文
     */
    void clearContext();

    /**
     * 获取当前上下文
     *
     * @return {@link SecurityContext}
     */
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
            Authentication authentication = getContext().getAuthentication();
            return AuthenticationUtils.isAuthenticated(authentication);
        } else {
            return false;
        }
    }
}
