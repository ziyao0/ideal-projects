package com.ziyao.ideal.encrypt;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.encrypt.core.CryptoContext;
import com.ziyao.ideal.encrypt.core.CryptoContextFactory;
import com.ziyao.ideal.encrypt.core.DefaultCryptoContextFactory;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import java.util.Map;

/**
 * @author ziyao zhang
 */
@Getter
public class EnvironmentDecryptPostProcessor
        extends AbstractEnvironmentDecryptor implements EnvironmentPostProcessor, Ordered {

    private final CryptoContextFactory contextFactory;

    public EnvironmentDecryptPostProcessor() {
        this.contextFactory = new DefaultCryptoContextFactory();
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // 创建解密上下文
        CryptoContext context = contextFactory.createContext(environment);

        MutablePropertySources propertySources = environment.getPropertySources();
        environment.getPropertySources().remove(CIPHER_PROPERTY_SOURCE_NAME);
        Map<String, Object> properties = decrypt(context, propertySources);
        if (!Collections.isEmpty(properties)) {
            environment.getPropertySources().addFirst(
                    new SystemEnvironmentPropertySource(
                            CIPHER_PROPERTY_SOURCE_NAME, properties)
            );
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
