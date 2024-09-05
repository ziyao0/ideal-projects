package com.ziyao.ideal.config;

import com.ziyao.ideal.web.checkstyle.ControllerParamChecker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("com.ziyao.ideal.config.domain.entity")
@EnableJpaRepositories("com.ziyao.ideal.config.repository.jpa")
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }


    @Bean
    public ControllerParamChecker controllerParamChecker() {
        return new ControllerParamChecker();
    }
}
