package com.ziyao.ideal.decipher.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.ziyao.ideal.core.lang.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;

/**
 * @author ziyao zhang
 */
public class DataSourceBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        if (bean instanceof DruidDataSource) {
            if (bean instanceof InitializingBean) {
                try {
                    ((InitializingBean) bean).afterPropertiesSet();
                } catch (Exception e) {
                    throw new BeanCreationException("create DruidDataSource invoke afterPropertiesSet error.", e);
                }
            }
            DruidDataSourceWrapper.init((DataSource) bean);
        }
        return bean;
    }

    public static class Registrar implements ImportBeanDefinitionRegistrar {

        private static final String BEAN_NAME = "dataSourceBeanPostProcessor";

        @Override
        public void registerBeanDefinitions(@NonNull AnnotationMetadata importingClassMetadata,
                                            BeanDefinitionRegistry registry) {
            if (!registry.containsBeanDefinition(BEAN_NAME)) {
                GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
                beanDefinition.setBeanClass(DataSourceBeanPostProcessor.class);
                beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
                beanDefinition.setSynthetic(true);
                registry.registerBeanDefinition(BEAN_NAME, beanDefinition);
            }
        }

    }
}
