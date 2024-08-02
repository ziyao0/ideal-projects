package com.ziyao.ideal.security.core.context;

import java.util.function.Supplier;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface DeferredSecurityContext extends Supplier<SecurityContext> {

    /**
     * Returns true if {@link #get()} refers to a generated {@link SecurityContext} or
     * false if it already existed.
     *
     * @return true if {@link #get()} refers to a generated {@link SecurityContext} or
     * false if it already existed
     */
    boolean isGenerated();
}
