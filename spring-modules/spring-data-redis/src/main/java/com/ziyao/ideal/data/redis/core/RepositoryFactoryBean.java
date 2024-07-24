package com.ziyao.ideal.data.redis.core;

import com.ziyao.ideal.core.Assert;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.lang.NonNull;

/**
 * @author ziyao
 */
@Getter
public class RepositoryFactoryBean<T extends RedisRepository<?, ?>>
        implements InitializingBean, FactoryBean<T>, BeanClassLoaderAware, BeanFactoryAware {

    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private RedisAdapter redisAdapter;
    private DefaultRepositoryFactory factory;
    private final Class<? extends T> repositoryInterface;
    private T repository;

    protected RepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
    }

    public void setRedisAdapter(RedisAdapter redisAdapter) {

        Assert.notNull(redisAdapter, "KeyValueOperations must not be null");

        this.redisAdapter = redisAdapter;
    }

    @Override
    public void setBeanClassLoader(@NonNull ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(@NonNull BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public T getObject() {
        return this.repository;
    }

    @Override
    public Class<?> getObjectType() {
        return this.repositoryInterface;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.factory = createRepositoryFactory();
        this.factory.setBeanClassLoader(classLoader);
        this.factory.setBeanFactory(beanFactory);
        this.repository = this.factory.getRepository(repositoryInterface);
    }

    private DefaultRepositoryFactory createRepositoryFactory() {
        Assert.notNull(redisAdapter, "operations are not initialized");
        return new DefaultRepositoryFactory(redisAdapter);
    }
}
