package com.ziyao.ideal.usercenter;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.context.SecurityContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author zhangziyao
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.ziyao.ideal.usercenter.repository.mapper")
@EntityScan(basePackages = "com.ziyao.ideal.usercenter.entity")
@EnableJpaRepositories(basePackages = "com.ziyao.ideal.usercenter.repository.jpa")
public class UserCenter {

    public static void main(String[] args) {
        System.setProperty(SecurityContextHolder.SYSTEM_PROPERTY, SecurityContextHolder.MODE_DEBUG);
        SpringApplication.run(UserCenter.class, args);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
    }

}
