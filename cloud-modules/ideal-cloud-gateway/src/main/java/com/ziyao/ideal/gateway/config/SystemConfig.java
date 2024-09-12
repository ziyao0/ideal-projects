package com.ziyao.ideal.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Data
@Configuration
@ConfigurationProperties("config.system")
public class SystemConfig {

    /**
     * 会话过期时间
     */
    private long sessionTimeout;

    /**
     * 缓存最大存储用户数量
     */
    private long maximumSize;
}