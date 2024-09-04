package com.ziyao.ideal.generator;

import com.ziyao.ideal.core.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class ConfigurationPropertiesBinder {

    private static final Logger log = LoggerFactory.getLogger(ConfigurationPropertiesBinder.class);

    public static <T> T bind(Class<T> configClass, String propertiesFileName) {
        T instance = null;
        try {
            instance = configClass.getDeclaredConstructor().newInstance();
            Properties properties = new Properties();
            String prefix = getPrefix(configClass);

            // 加载 properties 文件
            try (InputStream input = ConfigurationPropertiesBinder.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
                if (input == null) {
                    throw new IOException("Unable to find " + propertiesFileName);
                }
                properties.load(input);
            }

            // 反射遍历类的字段并绑定值
            for (Field field : configClass.getDeclaredFields()) {
                try {
                    String propertyName = prefix + "." + field.getName();
                    String propertyValue = properties.getProperty(propertyName);
                    if (Strings.hasText(propertyValue)) {
                        field.setAccessible(true);
                        Object value = convertValue(field.getType(), propertyValue);
                        field.set(instance, value);
                    }
                } catch (Exception e) {
                    log.error("给字段赋值异常：{}", e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            log.error("绑定配置异常：{}", e.getMessage(), e);
        }
        return instance;
    }

    private static String getPrefix(Class<?> configClass) {
        ConfigurationProperties annotation = configClass.getAnnotation(ConfigurationProperties.class);
        return annotation != null ? annotation.prefix() : "";
    }

    private static Object convertValue(Class<?> type, String value) {
        if (type == String.class) {
            return value;
        } else if (type == int.class || type == Integer.class) {
            return Integer.parseInt(value);
        } else if (type == boolean.class || type == Boolean.class) {
            return Boolean.parseBoolean(value);
        }
        throw new IllegalArgumentException("Unsupported field type: " + type.getName());
    }
}

