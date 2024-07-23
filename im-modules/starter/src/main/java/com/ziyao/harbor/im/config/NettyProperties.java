package com.ziyao.harbor.im.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ziyao zhang
 */
@Data
@ConfigurationProperties("ziyao.netty")
public class NettyProperties {

    private String serverAddr;
}
