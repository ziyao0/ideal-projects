package com.ziyao.ideal.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ziyao zhang
 */
@Data
@Configuration
@ConfigurationProperties("config.gateway.ip.blacklist")
public class IPBlacklistConfig {

    /**
     * 精准匹配
     * <p>
     * 192.168.31.1
     * 192.168.31.3
     */
    private Set<String> ips = new HashSet<>();
    /**
     * 模糊匹配
     * <p>
     * 192.168.31.*
     * 192.168.*.*
     * 192.*.*.*
     * *.*.*.*
     */
    private Set<String> ipSubnets = new HashSet<>();
    /**
     * ip范围
     * <p>
     * 192.168.31.1~192.168.31.100
     */
    private Set<String> ipLimits = new HashSet<>();
}
