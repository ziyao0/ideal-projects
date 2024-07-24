package com.ziyao.ideal.crypto.core;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author ziyao zhang
 */
public interface CryptoContextFactory {

    CryptoContext createContext(ConfigurableApplicationContext applicationContext);

    CryptoContext createContext(ConfigurableEnvironment environment);
}
