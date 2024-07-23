package com.ziyao.harbor.mq;

import com.ziyao.harbor.mq.config.MQConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ziyao zhang
 */
@SpringBootApplication
public class Stater implements CommandLineRunner {

    @Autowired
    private MQConfig mqConfig;

    public static void main(String[] args) {
        SpringApplication.run(Stater.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(mqConfig);
    }
}
