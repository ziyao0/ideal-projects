package com.ziyao.ideal.uaa;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.context.SecurityContextHolder;
import com.ziyao.ideal.security.core.context.StrategyMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * user account authentication
 *
 * @author zhangziyao
 */
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(basePackages = "com.ziyao.ideal.uaa.domain.entity")
@EnableJpaRepositories(basePackages = "com.ziyao.ideal.uaa.repository.jpa")
public class UAAStarter {

    public static void main(String[] args) {
        System.setProperty(SecurityContextHolder.SYSTEM_PROPERTY, StrategyMode.MODE_DEBUG.getStrategyName());
        SpringApplication.run(UAAStarter.class, args);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
    }

}
