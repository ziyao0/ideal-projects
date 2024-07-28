package com.ziyao.ideal.ai;


import com.ziyao.ideal.ai.chat.ChatService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ziyao zhang
 */
@SpringBootApplication
public class AiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        String proxy = "127.0.0.1";  // 示例，里面填具体的代理ip
        int port = 7890;   //代理的端口，
        System.setProperty("proxyType", "4");
        System.setProperty("proxyPort", Integer.toString(port));
        System.setProperty("proxyHost", proxy);
        System.setProperty("proxySet", "true");

        SpringApplication.run(AiApplication.class);
    }


    @Resource
    private ChatService chatService;

    @Override
    public void run(String... args) throws Exception {
        Object chat = chatService.chat("你在干什么？");
        System.out.println(chat);
    }
}
