package com.ziyao.ideal.uua.authentication.strategy;

import com.ziyao.ideal.uua.common.exception.InvalidCredentialsException;
import com.ziyao.ideal.uua.common.exception.InvalidUserStateException;
import com.ziyao.ideal.uua.common.exception.UnknownUserException;
import com.ziyao.ideal.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ziyao zhang
 */
@Component
public class AuthenticationFailureStrategyManager implements AuthenticationStrategyManager {

    private final Map<Class<? extends Exception>, AuthenticationFailureStrategy> strategies = new HashMap<>();


    public AuthenticationFailureStrategyManager() {
        // 注册所有策略
        strategies.put(InvalidCredentialsException.class, new InvalidCredentialsFailureStrategy());
        strategies.put(UnknownUserException.class, new UnknownUserFailureStrategy());
        strategies.put(InvalidUserStateException.class, new UnknownUserFailureStrategy());
        strategies.put(Exception.class, new ProgramExceptionFailureStrategy());
    }


    @Override
    public Authentication handle(Authentication authentication, Exception ex) {
        AuthenticationFailureStrategy authenticationFailureStrategy = this.strategies.get(ex.getClass());
        // 如果未获取到则获取默认
        if (authenticationFailureStrategy == null) {
            authenticationFailureStrategy = this.strategies.get(Exception.class);
        }
        return authenticationFailureStrategy.handleFailure(authentication, ex);
    }
}
