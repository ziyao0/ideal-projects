package com.ziyao.config.core;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class PropertiesConfigProcessor implements ConfigProcessor<Properties> {


    @Override
    public Properties load(String source) {
        try {
            Properties properties = new Properties();
            properties.load(new StringReader(source));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String resolve(Properties source) {
        return source.toString();
    }

    @Override
    public boolean isSupport(String fileType) {
        return false;
    }
}
