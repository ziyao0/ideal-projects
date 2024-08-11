package com.ziyao.ideal.gateway.filter.intercept;

/**
 * 拦截器
 *
 * @author ziyao zhang
 */
@FunctionalInterface
public interface GatewayInterceptor {

    /**
     * 拦截处理方法
     *
     * @param context 拦截上下文
     */
    void intercept(InterceptContext context);

    /**
     * 排序号
     *
     * @return 按照最小优先原则执行
     */
    default int getOrder() {
        return 0;
    }

}
