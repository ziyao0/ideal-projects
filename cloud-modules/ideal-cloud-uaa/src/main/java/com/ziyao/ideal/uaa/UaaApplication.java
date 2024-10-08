package com.ziyao.ideal.uaa;

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
public class UaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(UaaApplication.class, args);
    }

}
