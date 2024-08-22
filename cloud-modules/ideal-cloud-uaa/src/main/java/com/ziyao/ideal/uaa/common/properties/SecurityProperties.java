package com.ziyao.ideal.uaa.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ziyao zhang
 */
@Data
@Configuration
@ConfigurationProperties("security.config")
public class SecurityProperties {

}
