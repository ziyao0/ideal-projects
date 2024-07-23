package com.ziyao.gateway.core;

/**
 * 拦截器
 *
 * @author ziyao zhang
 */
@FunctionalInterface
public interface Interceptor<T> {

    void intercept(T t);

    /**
     * 排序号
     *
     * @return 按照最小优先原则执行
     */
    default int getOrder() {
        return 0;
    }

}
