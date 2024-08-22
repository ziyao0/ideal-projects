package com.ziyao.ideal.uua.authentication;

import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.uua.authentication.core.AuthenticatedUser;
import com.ziyao.ideal.uua.authentication.provider.AuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p>
 * 认证提供管理器，通过实现{@link AuthenticationManager} 认证管理器去处理认证核心逻辑
 * 认证实现是通过委托的设计模式去设计实现的，认证方案统一由{@link AuthenticationProvider}
 * 来提供.
 * 基于安全考虑，设计之初采用了双重认证（多因子认证）方案，即再{@link AuthenticationProvider#authenticate(Authentication)}
 * 认证通过的集成上提供了多因子认证方案，即通过{@link AuthenticationManager} 来提供多因子
 * 认证方案,执行{@linkplain AuthenticationManager#authenticate(Authentication)}该认证的前提是，首次认证的结果
 * {@link AuthenticatedUser#isAuthenticated()}必须为true，false或者null则代表在第一重验证的时候没有通过.
 * </p>
 *
 * @author ziyao zhang
 */
public class PrimaryAuthenticationManager implements AuthenticationManager {

    private static final Logger log = LoggerFactory.getLogger(PrimaryAuthenticationManager.class);

    private final List<AuthenticationProvider> authenticatorsProviders;

    public PrimaryAuthenticationManager(List<AuthenticationProvider> authenticatorsProviders) {
        Assert.notNull(authenticatorsProviders, "身份验证器不能为空");
        this.authenticatorsProviders = authenticatorsProviders;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {

        for (AuthenticationProvider authenticationProvider : this.authenticatorsProviders) {
            if (authenticationProvider.supports(authentication.getClass())) {
                return authenticationProvider.authenticate(authentication);
            }
        }
        log.error("没有支持的身份验证器：{}", authentication.getClass());
        return null;
    }
}
