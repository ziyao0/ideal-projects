package com.ziyao.ideal.boot.autoconfigure.redis.config;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.repository.config.XmlRepositoryConfigurationSource;

import java.util.Collection;

/**
 * @author ziyao zhang
 */
public interface RepositoryConfigurationExtension {


    <T extends RepositoryConfigurationSource> Collection<RepositoryConfiguration<T>> getRepositoryConfigurations(
            T configSource, ResourceLoader loader, boolean strictMatchesOnly);

    String getRepositoryFactoryBeanClassName();

    void registerBeansForRoot(BeanDefinitionRegistry registry, RepositoryConfigurationSource configurationSource);

    void postProcess(BeanDefinitionBuilder builder, RepositoryConfigurationSource config);

    void postProcess(BeanDefinitionBuilder builder, AnnotationRepositoryConfigurationSource config);

    void postProcess(BeanDefinitionBuilder builder, XmlRepositoryConfigurationSource config);
}
