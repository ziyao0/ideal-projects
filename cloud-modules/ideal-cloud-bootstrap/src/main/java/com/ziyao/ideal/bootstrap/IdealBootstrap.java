package com.ziyao.ideal.bootstrap;

import com.ziyao.ideal.config.ConfigInitiator;
import com.ziyao.ideal.uua.UAAInitiator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ziyao zhang
 */
@SpringBootApplication
public class IdealBootstrap {

    public static void main(String[] args) {

        ConfigurableApplicationContext configContext = SpringApplication.run(ConfigInitiator.class, args);

        ConfigurableApplicationContext uuaContext = SpringApplication.run(UAAInitiator.class, args);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            configContext.close();
            uuaContext.close();
        }));
    }
}
