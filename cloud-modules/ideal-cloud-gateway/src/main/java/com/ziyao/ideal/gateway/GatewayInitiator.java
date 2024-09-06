package com.ziyao.ideal.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhangziyao
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayInitiator {

    public static void main(String[] args) {
        SpringApplication.run(GatewayInitiator.class, args);
    }
}
