package com.ziyao.ideal.gateway;

import com.ziyao.ideal.gateway.authorization.convertor.AuthorizationConvertor;
import com.ziyao.ideal.gateway.authorization.convertor.DefaultAuthorizationConvertor;
import com.ziyao.ideal.gateway.authorization.convertor.DelegatingAuthorizationConvertor;
import com.ziyao.ideal.gateway.authorization.convertor.OAuth2AuthorizationConvertor;
import com.ziyao.ideal.gateway.core.cache.ReactiveBeans;
import com.ziyao.ideal.gateway.intercept.DelegatingInterceptor;
import com.ziyao.ideal.gateway.intercept.RequestInterceptor;
import com.ziyao.ideal.gateway.support.ApplicationContextUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.List;

/**
 * @author ziyao zhang
 */
@Configuration
public class GatewayAutoConfiguration implements ApplicationContextAware {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializer<String> keySerializer = new StringRedisSerializer();
        RedisSerializer<Object> valueSerializer = new GenericJackson2JsonRedisSerializer();
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 初始化 ReactiveStringRedisTemplate
     *
     * @param connectionFactory redis连接工厂
     * @return {@link ReactiveRedisConnectionFactory}
     */
    @Bean
    public ReactiveStringRedisTemplate reactiveStringRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        return new ReactiveStringRedisTemplate(connectionFactory);
    }


    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.setApplicationContext(applicationContext);
    }

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");//允许所有请求头
        config.addAllowedOrigin("*");//允许所有请求方法，例如get，post等
        config.addAllowedHeader("*");//允许所有的请求来源
        config.setAllowCredentials(true);//允许携带cookie

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);//对所有经过网关的请求都生效
        return new CorsWebFilter(source);
    }

    @Bean
    @Primary
    public RequestInterceptor requestInterceptor() {
        List<RequestInterceptor> authorizationProviders =
                ApplicationContextUtils.getBeansOfType(RequestInterceptor.class);
        return new DelegatingInterceptor(authorizationProviders);
    }

    @Bean
    public AuthorizationConvertor authorizationConvertor() {
        return new DelegatingAuthorizationConvertor(
                List.of(new DefaultAuthorizationConvertor(), new OAuth2AuthorizationConvertor())
        );
    }

    @Bean
    @ConditionalOnClass({ReactiveRedisConnectionFactory.class, ReactiveRedisTemplate.class})
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        ReactiveRedisTemplate<String, Object> reactiveRedisTemplate = new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, redisSerializationContext());
        ReactiveBeans.setRedis(reactiveRedisTemplate);
        ReactiveBeans.setStringRedis(reactiveStringRedisTemplate(reactiveRedisConnectionFactory));
        return reactiveRedisTemplate;
    }

    /**
     * redis reactive serialization
     * <p>
     * 默认json序列化
     * <code>
     * final GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
     * </code>
     *
     * @return {@link RedisSerializationContext}
     * @see RedisSerializationContext.RedisSerializationContextBuilder
     */
    public RedisSerializationContext<String, Object> redisSerializationContext() {
        // 对象序列化器
        final RedisSerializationContext.SerializationPair<Object> objectSerializationPair
                = RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json());
        // 字符串序列化器
        final RedisSerializationContext.SerializationPair<String> stringSerializationPair
                = RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer.UTF_8);
        RedisSerializationContext.RedisSerializationContextBuilder<String, Object> builder =
                RedisSerializationContext.newSerializationContext();

        builder.hashKey(stringSerializationPair);
        builder.hashValue(objectSerializationPair);
        builder.key(stringSerializationPair);
        builder.value(objectSerializationPair);
        builder.string(stringSerializationPair);
        return builder.build();
    }

}
