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

    private static void initialize() {
        initializeStrategy();
        initializeCount++;
    }

    private static void initializeStrategy() {
        // 默认使用当前线程
        strategy = createStrategy(strategyName);
    }


    public static void clearContext() {
        strategy.clearContext();
    }


    public static SecurityContext getContext() {
        return strategy.getContext();
    }


    public static DeferredSecurityContext getDeferredContext() {
        return strategy.getDeferredContext();
    }


    public static void setContext(SecurityContext context) {
        strategy.setContext(context);
    }


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

    public static boolean isAuthorized() {
        return strategy.isAuthentication();
    }

    public static boolean unauthorized() {
        return !strategy.isAuthentication();
    }

    public static void switchTTLStrategy() {
        switchStrategy(StrategyMode.MODE_TTL);
    }

    public static void switchDefaultStrategy() {
        switchStrategy(StrategyMode.MODE_TTL);
    }

    public static void switchStrategy(StrategyMode strategyMode) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        strategy = doCreateStrategy(strategyMode);
        strategy.setContext(securityContext);
    }

    public static SecurityContextHolderStrategy createStrategy(String strategyName) {
        StrategyMode strategyMode = StrategyMode.getInstance(strategyName);
        return doCreateStrategy(strategyMode);
    }

    public static SecurityContextHolderStrategy doCreateStrategy(StrategyMode strategyMode) {

        switch (strategyMode) {
            case MODE_TTL: {
                return new TTLSecurityContextHolderStrategy();
            }
            case MODE_DEBUG: {
                return new DebugLocalSecurityContextHolderStrategy();
            }
            case MODE_THREAD_LOCAL: {
                return new ThreadLocalSecurityContextHolderStrategy();
            }
            default: {
                log.warn("未知的策略名称：{}", strategyName);
                return new ThreadLocalSecurityContextHolderStrategy();
            }
        }
    }

    private SecurityContextHolder() {
    }
}
