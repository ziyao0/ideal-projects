package com.ziyao.ideal.decipher.core;

import com.ziyao.ideal.crypto.PropertyResolver;
import com.ziyao.ideal.crypto.TextCipher;
import com.ziyao.ideal.crypto.TextCipherProvider;
import com.ziyao.ideal.decipher.EnvironmentExtractor;
import com.ziyao.ideal.decipher.utils.CipherAssert;
import com.ziyao.ideal.decipher.utils.TextCipherUtils;
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
        CodebookProperties local = EnvironmentExtractor.extractProperties(environment, CodebookProperties.class);
        CodebookProperties external = null;
        if (null != local) {
            external = EnvironmentExtractor.extractProperties(local.getLocation(), CodebookProperties.class);
        }
        // 检查配置
        if (local != null && local.isCheckCipher()) {
            CipherAssert.assertLegal(local, external);
        }
        // 合并配置
        return CodebookProperties.merge(local, external);
    }
}
