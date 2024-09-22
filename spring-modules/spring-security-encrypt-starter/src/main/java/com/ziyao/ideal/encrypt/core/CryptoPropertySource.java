package com.ziyao.ideal.encrypt.core;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.crypto.Properties;
import org.springframework.core.env.PropertySource;

/**
 * @author ziyao zhang
 */
public class CryptoPropertySource extends PropertySource<Properties<?>> {


    public CryptoPropertySource(String name, Properties<?> source) {
        super(name, source);
    }

    @NonNull
    @Override
    public Properties<?> getProperty(@NonNull String name) {
        return this.source;
    }
}
