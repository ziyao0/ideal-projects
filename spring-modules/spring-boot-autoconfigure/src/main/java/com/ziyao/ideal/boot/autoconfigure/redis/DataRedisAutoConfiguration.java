package com.ziyao.ideal.boot.autoconfigure.redis;

import com.ziyao.ideal.data.redis.core.CacheBeanName;
import com.ziyao.ideal.data.redis.core.DefaultRedisAdapter;
import com.ziyao.ideal.data.redis.core.RedisAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author ziyao zhang
 */
@Configuration
@ConditionalOnClass({RedisTemplate.class})
@Import(RedisRepositoriesRegistrar.class)
public class DataRedisAutoConfiguration {


    @Bean(name = CacheBeanName.cache_name)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(RedisSerializer.byteArray());
        redisTemplate.setHashKeySerializer(RedisSerializer.byteArray());
        redisTemplate.setValueSerializer(RedisSerializer.byteArray());
        redisTemplate.setHashValueSerializer(RedisSerializer.byteArray());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }


    @Bean
    public RedisAdapter redisAdapter(RedisTemplate<String, Object> redisTemplate) {
        return new DefaultRedisAdapter(redisTemplate);
    }
}
