package com.ziyao.ideal.gateway.authorization;

/**
 * @author ziyao zhang
 */
public interface AuthorizationProvider {

    /**
     * 授权处理
     *
     * @param authorization {@link Authorization}授权对象
     * @return 返回认证结果
     */
    Authorization authorize(Authorization authorization);

    /**
     * 判断是否支持处理当前处理逻辑
     *
     * @param authorizationClass 授权类
     * @return true 支持当前处理
     */
    boolean supports(Class<? extends Authorization> authorizationClass);
}
