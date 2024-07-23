package com.ziyao.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigureStarter {


    public static void main(String[] args) {
        SpringApplication.run(ConfigureStarter.class, args);
    }

}
