package com.ziyao.ideal.bootstrap;

import com.ziyao.ideal.config.ConfigInitiator;
import com.ziyao.ideal.uua.UAAInitiator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * @author ziyao zhang
 */
@SpringBootApplication
public class IdealBootstrap {

    public static void main(String[] args) {
        System.setProperty("spring.jmx.enabled", "false");
        SpringApplication configApp = new SpringApplication(ConfigInitiator.class);

        configApp.addInitializers(context ->
                context.addApplicationListener((ApplicationListener<ContextClosedEvent>) event ->
                        System.setProperty("spring.jmx.enabled", "false")));

        configApp.run(args);

        SpringApplication uaaApp = new SpringApplication(UAAInitiator.class);

        uaaApp.addInitializers(context ->
                context.addApplicationListener((ApplicationListener<ContextClosedEvent>) event ->
                        System.setProperty("spring.jmx.enabled", "false")));

        uaaApp.run(args);
    }
}
