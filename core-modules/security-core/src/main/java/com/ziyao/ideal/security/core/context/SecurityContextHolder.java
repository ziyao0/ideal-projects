package com.ziyao.ideal.security.core.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ziyao zhang
 */
public abstract class SecurityContextHolder {

    private static final Logger log = LoggerFactory.getLogger(SecurityContextHolder.class);

    public static final String SYSTEM_PROPERTY = "security.strategy";

    private static final String strategyName = System.getProperty(SYSTEM_PROPERTY);

    private static SecurityContextHolderStrategy strategy;

    private static int initializeCount = 0;

    static {
        initialize();
    }

    /**
     * 初始化
     */
    private static void initialize() {
        initializeStrategy();
        initializeCount++;
    }

    /**
     * 初始化创建SecurityContextHolderStrategy
     */
    private static void initializeStrategy() {
        // 默认使用当前线程
        strategy = createStrategy(strategyName);
    }

    /**
     * 清理当前上下文信息
     */
    public static void clearContext() {
        strategy.clearContext();
    }

    /**
     * 获取当前上下文信息
     *
     * @return {@link SecurityContext}
     */
    public static SecurityContext getContext() {
        return strategy.getContext();
    }

    /**
     * 获取延迟的上下文信息
     *
     * @return {@link DeferredSecurityContext}
     */
    public static DeferredSecurityContext getDeferredContext() {
        return strategy.getDeferredContext();
    }

    /**
     * 设置当前上下文信息
     *
     * @param context 安全上下文详情
     */
    public static void setContext(SecurityContext context) {
        strategy.setContext(context);
    }

    /**
     * 设置延迟的上下文信息
     *
     * @param deferredContext {@link DeferredSecurityContext}
     */
    public static void setDeferredContext(DeferredSecurityContext deferredContext) {
        strategy.setDeferredContext(deferredContext);
    }

    /**
     * Allows retrieval of the context strategy. See SEC-1188.
     *
     * @return the configured strategy for storing the security context.
     */
    public static SecurityContextHolderStrategy getContextHolderStrategy() {
        return strategy;
    }

    public static SecurityContext createEmptyContext() {
        return strategy.createEmptyContext();
    }

    /**
     * 判断当前上下文信息持有者是否认证
     *
     * @return <code>true</code> 经过身份认证
     */
    public static boolean isAuthorized() {
        return strategy.isAuthentication();
    }

    /**
     * 判断当前上下文信息持有者是否未经过
     *
     * @return <code>true</code> 未经过身份认证
     */
    public static boolean unauthorized() {
        return !strategy.isAuthentication();
    }

    /**
     * 断言当前请求是否已经经过身份认证
     */
    public static void assertAuthorized() {
        if (unauthorized()) {
            throw new RuntimeException("当前用户未登录");
        }
    }

    /**
     * 切换到ttl模式
     *
     * @see StrategyMode
     */
    public static void switchTTLStrategy() {
        switchStrategy(StrategyMode.MODE_TTL);
    }

    /**
     * 切换到默认策略
     *
     * @see StrategyMode
     */
    public static void switchDefaultStrategy() {
        switchStrategy(StrategyMode.MODE_THREAD_LOCAL);
    }

    /**
     * 切换到指定的策略
     *
     * @param strategyMode {@link StrategyMode}
     */
    public static void switchStrategy(StrategyMode strategyMode) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        strategy = doCreateStrategy(strategyMode);
        strategy.setContext(securityContext);
    }

    /**
     * 创建 SecurityContextHolderStrategy
     *
     * @param strategyName 策略名称
     * @return <code>SecurityContextHolderStrategy</code>
     */
    public static SecurityContextHolderStrategy createStrategy(String strategyName) {
        StrategyMode strategyMode = StrategyMode.getInstance(strategyName);
        return doCreateStrategy(strategyMode);
    }

    /**
     * 创建策略
     *
     * @param strategyMode 策略模式
     * @return <code>SecurityContextHolderStrategy</code>
     */
    public static SecurityContextHolderStrategy doCreateStrategy(StrategyMode strategyMode) {

        switch (strategyMode) {
            case MODE_TTL -> {
                return new TTLSecurityContextHolderStrategy();
            }
            case MODE_DEBUG -> {
                return new DebugLocalSecurityContextHolderStrategy();
            }
            case MODE_THREAD_LOCAL -> {
                return new ThreadLocalSecurityContextHolderStrategy();
            }
            default -> {
                log.warn("未知的策略名称：{}", strategyName);
                return new ThreadLocalSecurityContextHolderStrategy();
            }
        }
    }

    private SecurityContextHolder() {
    }
}
