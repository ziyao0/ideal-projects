package com.ziyao.ideal.uaa.authentication.strategy;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.web.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ziyao zhang
 */
@Slf4j
@Component
public class AuthenticationFailureStrategyManager implements AuthenticationStrategyManager {

    /**
     * 包含除默认策略外所有
     */
    private final List<AuthenticationFailureStrategy> authenticationFailureStrategies = new ArrayList<>();
    /**
     * 默认认证异常策略
     * <p>
     * 处理因为不可控因素引起的异常，如程序异常或者其他未知条件引起的程序报错
     */
    public AuthenticationFailureStrategy defaultAuthenticationFailureStrategy;


    public AuthenticationFailureStrategyManager() {
        // 注册所有策略
        List<AuthenticationFailureStrategy> strategies = ApplicationContextUtils.getBeansOfType(AuthenticationFailureStrategy.class);
        for (AuthenticationFailureStrategy authenticationFailureStrategy : strategies) {
            if (authenticationFailureStrategy.isSupport(Exception.class)) {
                this.defaultAuthenticationFailureStrategy = authenticationFailureStrategy;
            } else {
                this.authenticationFailureStrategies.add(authenticationFailureStrategy);
            }
        }
    }


    @Override
    public Authentication handle(Authentication authentication, Exception ex) {

        for (AuthenticationFailureStrategy authenticationFailureStrategy : this.authenticationFailureStrategies) {
            if (authenticationFailureStrategy.isSupport(ex.getClass())) {
                return authenticationFailureStrategy.handleFailure(authentication, ex);
            }
        }
        return defaultAuthenticationFailureStrategy.handleFailure(authentication, ex);
    }
}
