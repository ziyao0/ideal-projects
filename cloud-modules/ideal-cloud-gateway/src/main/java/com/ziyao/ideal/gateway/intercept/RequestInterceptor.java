package com.ziyao.ideal.gateway.intercept;

import com.ziyao.ideal.gateway.authorization.Authorization;

/**
 * 拦截器
 *
 * @author ziyao zhang
 */
@FunctionalInterface
public interface RequestInterceptor {

    /**
     * 拦截处理方法
     *
     * @param authorization 授权上下文
     */
    void intercept(Authorization authorization);

    /**
     * 排序号
     *
     * @return 按照最小优先原则执行
     */
    default int getOrder() {
        return 0;
    }

}
