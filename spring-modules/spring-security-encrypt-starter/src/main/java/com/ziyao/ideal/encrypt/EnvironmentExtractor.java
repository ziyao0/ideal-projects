package com.ziyao.ideal.encrypt;

import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.crypto.Properties;
import com.ziyao.ideal.encrypt.core.CryptoPropertySource;
import com.ziyao.ideal.encrypt.utils.ConstantPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;

/**
 * @author ziyao zhang
 */
public abstract class EnvironmentExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnvironmentExtractor.class);

    /**
     * 从环境变量中提取属性配置
     *
     * @param environment 环境配置信息
     * @return {@link Properties}
     */
    public static Properties<?> extract(ConfigurableEnvironment environment) {
        try {
            if (null == environment) {
                return null;
            }
            return doExtract(environment);
        } catch (Exception e) {
            LOGGER.error("提取配置异常！", e);
        }
        return null;
    }

    private static Properties<?> doExtract(ConfigurableEnvironment environment) {

        CryptoPropertySource propertySource = extractPropertySourceAndRemove(environment);

        Properties<?> properties = propertySource.getProperty(ConstantPool.properties_prefix);
        return Binder.get(environment)
                .bind(properties.getPrefix(), Bindable.of(properties.getClass()))
                .orElseGet(() -> null);
    }

    private static CryptoPropertySource extractPropertySourceAndRemove(ConfigurableEnvironment environment) {
        CryptoPropertySource propertySource = (CryptoPropertySource) environment.getPropertySources()
                .get(ConstantPool.properties_prefix);
        environment.getPropertySources().remove(ConstantPool.properties_prefix);
        Assert.notNull(propertySource, "CipherPropertySource is not null");
        return propertySource;
    }

    /**
     * 从环境变量中提取密码相关配置
     *
     * @param environment 环境变量
     * @return {@link Properties}
     */
    @SuppressWarnings("unchecked")
    public static <T> T extractProperties(ConfigurableEnvironment environment, Class<? extends Properties<T>> clazz) {

        injectEnvironment(environment, clazz);
        // 提取属性
        return (T) extract(environment);

    }

    private static <T> void injectEnvironment(ConfigurableEnvironment environment, Class<? extends Properties<T>> clazz) {
        try {
            Properties<T> properties = clazz.getDeclaredConstructor().newInstance();
            environment.getPropertySources().addFirst(new CryptoPropertySource(ConstantPool.properties_prefix, properties));
        } catch (Exception e) {
            LOGGER.error("实例化对象异常：{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 提取属性配置
     *
     * @param configPath 配置文件路径
     * @param clazz      配置类型
     * @param <T>        property type
     * @return 返回获取到的配置信息
     */
    public static <T> T extractProperties(String configPath, Class<? extends Properties<T>> clazz) {
        // 从文件路径获取配置文件
        File propertiesFile = getPropertiesFile(configPath);
        if (null == propertiesFile) {
            return null;
        } else
            try {
                ConfigurableEnvironment environment = extractYamlProperties(propertiesFile);
                return extractProperties(environment, clazz);
            } catch (Exception e) {
                LOGGER.error("读取配置异常异常：{}", e.getMessage(), e);
                throw new RuntimeException(e);
            }
    }

    /**
     * 从文件中提取环境变量
     *
     * @param propertiesFile 配置文件
     * @return 返回 {@link Environment}
     * @throws IOException 读取配置文件失败后所抛出的流异常
     */
    private static ConfigurableEnvironment extractYamlProperties(File propertiesFile) throws IOException {
        if (propertiesFile == null) {
            return null;
        }
        StandardEnvironment environment = new StandardEnvironment();
        YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
        yamlPropertySourceLoader.load("EnvironmentExtractor", new FileSystemResource(propertiesFile))
                .forEach(e -> {
                    environment.getPropertySources().addFirst(e);
                });
        return environment;
    }

    /**
     * 获取配置文件
     *
     * @param configPath 配置文件路径
     * @return 如果存在返回当前配置文件信息
     */
    private static File getPropertiesFile(String configPath) {
        if (Strings.hasText(configPath))
            return getPropertiesFileForConfigPath(configPath);
        else
            return null;
    }

    /**
     * 通过路径获取配置文件
     *
     * @param configPath 文件路径
     * @return 返回文件信息
     */
    private static File getPropertiesFileForConfigPath(String configPath) {
        File file = new File(configPath);
        if (file.exists() && file.isFile())
            return file;
        else
            return null;
    }
}
