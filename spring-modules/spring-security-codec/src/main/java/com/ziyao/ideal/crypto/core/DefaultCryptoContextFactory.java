package com.ziyao.ideal.crypto.core;

import com.ziyao.ideal.crypto.EnvironmentExtractor;
import com.ziyao.crypto.PropertyResolver;
import com.ziyao.crypto.TextCipher;
import com.ziyao.crypto.TextCipherProvider;
import com.ziyao.ideal.crypto.utils.TextCipherUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;

/**
 * @author ziyao zhang
 */
public class DefaultCryptoContextFactory implements CryptoContextFactory {


    @Override
    public CryptoContext createContext(ConfigurableApplicationContext applicationContext) {
        return createContext(applicationContext.getEnvironment());
    }

    @Override
    public CryptoContext createContext(ConfigurableEnvironment environment) {
        CodebookProperties properties = loadEncryptorPropertiesInEnvironment(environment);
        TextCipherProvider textCipherProvider = createTextCipherProvider(properties);

        ConfigurableCryptoContext context = new ConfigurableCryptoContext();
        context.setProperties(properties);
        context.setTextCipherProvider(textCipherProvider);
        context.setPropertyResolver(new PropertyResolver(textCipherProvider));
        context.setFailOnError(properties.isFailOnError());
        return context;
    }

    private TextCipherProvider createTextCipherProvider(CodebookProperties properties) {
        List<TextCipher> textCiphers = TextCipherUtils.loadCipher(properties);
        return new TextCipherProvider(textCiphers);
    }

    private CodebookProperties loadEncryptorPropertiesInEnvironment(ConfigurableEnvironment environment) {
        CodebookProperties main = EnvironmentExtractor.extractProperties(environment, CodebookProperties.class);
        CodebookProperties external = null;
        if (null != main) {
            external = EnvironmentExtractor.extractProperties(main.getLocation(), CodebookProperties.class);
        }
        // 合并配置
        return CodebookProperties.merge(main, external);
    }
}
