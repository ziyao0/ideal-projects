package com.ziyao.harbor.usercenter;

import com.ziyao.harbor.usercenter.authentication.AuthenticationHandler;
import com.ziyao.harbor.usercenter.authentication.AuthenticationManager;
import com.ziyao.harbor.usercenter.authentication.DefaultAuthenticationHandler;
import com.ziyao.harbor.usercenter.authentication.PrimaryAuthenticationManager;
import com.ziyao.harbor.usercenter.authentication.converter.*;
import com.ziyao.harbor.usercenter.authentication.processors.AuthenticationPostProcessor;
import com.ziyao.harbor.usercenter.authentication.processors.DelegatingPostProcessor;
import com.ziyao.harbor.usercenter.authentication.provider.AuthenticationProvider;
import com.ziyao.harbor.usercenter.authentication.strategy.AuthenticationStrategyManager;
import com.ziyao.harbor.usercenter.authentication.token.*;
import com.ziyao.harbor.usercenter.repository.jpa.ApplicationRepositoryJpa;
import com.ziyao.harbor.usercenter.repository.jpa.AuthorizationRepositoryJpa;
import com.ziyao.harbor.usercenter.repository.redis.ApplicationRepositoryRedis;
import com.ziyao.harbor.usercenter.repository.redis.AuthorizationRepositoryRedis;
import com.ziyao.harbor.usercenter.service.app.*;
import com.ziyao.harbor.usercenter.service.oauth2.*;
import com.ziyao.harbor.web.ApplicationContextUtils;
import com.ziyao.security.oauth2.core.OAuth2Token;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author zhangziyao
 */
@Configuration
public class OAuth2AutoConfiguration implements ApplicationContextAware {

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
                List.of(new JpaRegisteredAppService(applicationRepositoryJpa),
                        new RedisRegisteredAppService(applicationRepositoryRedis),
                        new CaffeineRegisteredAppService())
        );
    }

    @Bean
    public OAuth2AuthorizationService delegatingOAuth2AuthorizationService(AuthorizationRepositoryJpa authorizationRepository,
                                                                           AuthorizationRepositoryRedis oAuth2AuthorizationRepository) {
        return new DelegatingOAuth2AuthorizationService(
                List.of(
                        new JpaOAuth2AuthorizationService(authorizationRepository),
                        new RedisOAuth2AuthorizationService(oAuth2AuthorizationRepository),
                        new CaffeineOAuth2AuthorizationService()
                )
        );
    }

    @Bean
    public AuthenticationConverter authenticationConverter() {
        return new DelegatingAuthenticationConverter(
                List.of(
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
