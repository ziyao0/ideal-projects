package com.ziyao.ideal.gateway.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author ziyao zhang
 */
@Getter
@Component
@RequiredArgsConstructor
public class ConfigCenter {

    private final GatewayConfig gatewayConfig;
    private final IPBlacklistConfig ipBlacklistConfig;
    private final LoggerConfig loggerConfig;
    private final SystemConfig systemConfig;

}
