package com.ziyao.ideal.config;

import com.ziyao.crypto.Property;
import com.ziyao.ideal.config.core.YamlConfigProcessor;
import com.ziyao.ideal.config.manager.ConfigManager;
import com.ziyao.ideal.web.checkstyle.ControllerParamChecker;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("com.ziyao.ideal.config.domain.entity")
@EnableJpaRepositories("com.ziyao.ideal.config.repository.jpa")
public class ConfigInitiator implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConfigInitiator.class, args);
    }


    @Bean
    public ControllerParamChecker controllerParamChecker() {
        return new ControllerParamChecker();
    }

    @Resource
    private ConfigManager configManager;

    @Override
    public void run(String... args) throws Exception {

        String config = configManager.getConfig("database.yml", null);
        String config2 = configManager.getConfig("redis.yml", null);

        YamlConfigProcessor processor = new YamlConfigProcessor();

        List<Property> load = processor.load(config);

        for (Property property : load) {
            System.out.println(property.getKey() + ":" + property.getValue());
        }

        List<Property> load1 = processor.load(config2);
        for (Property property : load1) {
            System.out.println(property.getKey() + ":" + property.getValue());
        }
    }
}
