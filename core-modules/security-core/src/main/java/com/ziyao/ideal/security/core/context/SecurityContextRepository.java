package com.ziyao.ideal.security.core.context;

import org.springframework.util.function.SingletonSupplier;

import java.util.function.Supplier;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface SecurityContextRepository<T> {

    /**
     * Gets the security context for the current request (if available) and returns it.
     * <p>
     * If the session is null, the context object is null or the context object stored in
     * the session is not an instance of {@code SecurityContext}, a new context object
     * will be generated and returned.
     */
    SecurityContext loadContext(T request);

    /**
     * Defers loading the {@link SecurityContext}
     *
     * @return a {@link DeferredSecurityContext} that returns the {@link SecurityContext}
     * which cannot be null
     */
    default DeferredSecurityContext loadDeferredContext(T request) {
        Supplier<SecurityContext> supplier = () -> loadContext(request);
        return new SupplierDeferredSecurityContext(SingletonSupplier.of(supplier),
                SecurityContextHolder.getContextHolderStrategy());
    }

    /**
     * Stores the security context on completion of a request.
     *
     * @param context the non-null context which was obtained from the holder.
     */
    void saveContext(SecurityContext context);

    /**
     * Allows the repository to be queried as to whether it contains a security context
     * for the current request.
     *
     * @param request the current request
     * @return true if a context is found for the request, false otherwise
     */
    boolean containsContext(T request);
}
