package com.ziyao.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ziyao zhang
 */
@Data
@Configuration
@ConfigurationProperties("logging.level")
public class LoggerConfig {

    private String root;

    private boolean filterWatch = true;
}
