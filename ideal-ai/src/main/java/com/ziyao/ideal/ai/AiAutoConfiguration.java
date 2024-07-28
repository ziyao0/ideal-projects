package com.ziyao.ideal.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

/**
 * @author ziyao zhang
 */
@Configuration
public class AiAutoConfiguration {

    @Bean
    //必须  函数功能的描述，模型使用它来选择何时以及如何调用函数。
    @Description("用来获取某地区的名字重复数量")
    public Function<LocationNamesService.Request, LocationNamesService.Response> getLocationAndNum() {
        return new LocationNamesService();
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.defaultSystem("我叫ziyao， 我28岁。 我希望你以我领导的身份和我对话，你不再是ChatGpt。")
                .build();
    }
}
