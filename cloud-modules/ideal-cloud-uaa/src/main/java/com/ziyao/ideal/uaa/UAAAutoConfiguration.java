package com.ziyao.ideal.uaa;

import com.google.common.collect.Lists;
import com.ziyao.ideal.security.oauth2.core.OAuth2Token;
import com.ziyao.ideal.uaa.authentication.AuthenticationHandler;
import com.ziyao.ideal.uaa.authentication.AuthenticationManager;
import com.ziyao.ideal.uaa.authentication.DefaultAuthenticationHandler;
import com.ziyao.ideal.uaa.authentication.PrimaryAuthenticationManager;
import com.ziyao.ideal.uaa.authentication.converter.*;
import com.ziyao.ideal.uaa.authentication.processors.AuthenticationPostProcessor;
import com.ziyao.ideal.uaa.authentication.processors.DelegatingPostProcessor;
import com.ziyao.ideal.uaa.authentication.provider.AuthenticationProvider;
import com.ziyao.ideal.uaa.authentication.strategy.AuthenticationStrategyManager;
import com.ziyao.ideal.uaa.authentication.token.*;
import com.ziyao.ideal.uaa.repository.jpa.ApplicationRepositoryJpa;
import com.ziyao.ideal.uaa.repository.jpa.AuthorizationRepositoryJpa;
import com.ziyao.ideal.uaa.repository.redis.ApplicationRepositoryRedis;
import com.ziyao.ideal.uaa.repository.redis.AuthorizationRepositoryRedis;
import com.ziyao.ideal.uaa.service.app.*;
import com.ziyao.ideal.uaa.service.oauth2.*;
import com.ziyao.ideal.web.ApplicationContextUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * user account and authentication autoconfiguration
 *
 * @author zhangziyao
 */
@Configuration
public class UAAAutoConfiguration implements ApplicationContextAware {

    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.setApplicationContext(applicationContext);
    }

    @Bean
    public OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator() {
        return new DelegatingOAuth2TokenGenerator(
                new OAuth2AuthorizationCodeGenerator(),
                new OAuth2AccessTokenGenerator(),
                new OAuth2RefreshTokenGenerator()
        );
    }

    @Bean
    public RegisteredAppService delegatingRegisteredAppService(
            ApplicationRepositoryJpa applicationRepositoryJpa, ApplicationRepositoryRedis applicationRepositoryRedis) {

        return new DelegatingRegisteredAppService(
                Lists.newArrayList(new JpaRegisteredAppService(applicationRepositoryJpa),
                        new RedisRegisteredAppService(applicationRepositoryRedis),
                        new CaffeineRegisteredAppService())
        );
    }

    @Bean
    public OAuth2AuthorizationService delegatingOAuth2AuthorizationService(AuthorizationRepositoryJpa authorizationRepositoryJpa,
                                                                           AuthorizationRepositoryRedis authorizationRepositoryRedis) {
        return new DelegatingOAuth2AuthorizationService(
                Lists.newArrayList(
                        new JpaOAuth2AuthorizationService(authorizationRepositoryJpa),
                        new RedisOAuth2AuthorizationService(authorizationRepositoryRedis),
                        new CaffeineOAuth2AuthorizationService()
                )
        );
    }

    @Bean
    public AuthenticationConverter authenticationConverter() {
        return new DelegatingAuthenticationConverter(
                Lists.newArrayList(
                        new UsernamePasswordAuthenticationConverter(),
                        new OAuth2AuthorizationCodeAuthenticationConverter(),
                        new OAuth2RefreshTokenAuthenticationConverter()
                )
        );
    }


    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> authenticators =
                ApplicationContextUtils.getBeansOfType(AuthenticationProvider.class);
        return new PrimaryAuthenticationManager(authenticators);
    }

    @Bean
    public AuthenticationPostProcessor delegatingPostProcessor() {
        List<AuthenticationPostProcessor> processors =
                ApplicationContextUtils.getBeansOfType(AuthenticationPostProcessor.class);
        return new DelegatingPostProcessor(processors);
    }

    @Bean
    public AuthenticationHandler authenticationHandler(
            AuthenticationStrategyManager authenticationStrategyManager) {

        return new DefaultAuthenticationHandler(
                delegatingPostProcessor(), authenticationStrategyManager);
    }
}
