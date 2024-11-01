package com.ziyao.ideal.decipher.boot.autoconfigure;

import com.ziyao.ideal.decipher.EnvironmentDecipherApplicationInitializer;
import com.ziyao.ideal.decipher.core.CodebookProperties;
import com.ziyao.ideal.decipher.core.CryptoContextFactory;
import com.ziyao.ideal.decipher.core.DefaultCryptoContextFactory;
import com.ziyao.ideal.decipher.druid.DataSourceBeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.bootstrap.encrypt.EncryptionBootstrapConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author ziyao zhang
 */
@Configuration
@AutoConfigureBefore(EncryptionBootstrapConfiguration.class)
@EnableConfigurationProperties(CodebookProperties.class)
public class DecryptionBootstrap2AutoConfiguration {


    @Bean
    @ConditionalOnClass(name = {
            "com.ziyao.ideal.crypto.TextCipher",
            "com.ziyao.ideal.core.codec.StringCodec"
    })
    public CryptoContextFactory cipherContextFactory() {
        return new DefaultCryptoContextFactory();
    }

    @Bean
    public EnvironmentDecipherApplicationInitializer codecEnvironmentApplicationInitializer() {
        return new EnvironmentDecipherApplicationInitializer(cipherContextFactory());
    }

    @Configuration
    @ConditionalOnClass(name = {
            "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration",
            "com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure"
    })
    @ConditionalOnBean(CryptoContextFactory.class)
    @Import(DataSourceBeanPostProcessor.Registrar.class)
    public static class DataSourceAutoConfiguration {

    }
}
