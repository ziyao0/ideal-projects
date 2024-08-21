package com.ziyao.ideal.mq;

import com.ziyao.ideal.mq.api.MQClient;
import com.ziyao.ideal.mq.config.KafkaConfig;
import com.ziyao.ideal.mq.config.MQConfig;
import com.ziyao.ideal.mq.config.RabbitMQConfig;
import com.ziyao.ideal.mq.config.RocketMQConfig;
import com.ziyao.ideal.mq.core.KafkaClient;
import com.ziyao.ideal.mq.core.RabbitMQClient;
import com.ziyao.ideal.mq.core.RocketMQClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author ziyao zhang
 */
@Configuration
@Import({MQConfig.class, RocketMQConfig.class, KafkaConfig.class, RabbitMQConfig.class})
public class MQAutoConfiguration {

    @Bean
    public MQClient rocketMQClient(MQConfig config) {
        switch (config.getMqType()) {
            // 初始化rocketmq client
            case ROCKET_MQ: {
                return new RocketMQClient();
            }
            // 初始化kafka client
            case KAFKA: {
                return new KafkaClient();
            }
            // 初始化 rabbit client
            case RABBIT_MQ: {
                return new RabbitMQClient();
            }
            default: {
                return null;
            }
        }
    }

}
