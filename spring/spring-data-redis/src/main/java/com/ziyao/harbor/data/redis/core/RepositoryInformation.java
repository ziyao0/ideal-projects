package com.ziyao.harbor.data.redis.core;

/**
 * @author ziyao
 */
public interface RepositoryInformation extends RepositoryMetadata {
    /**
     * Returns the base class to be used to create the proxy backing instance.
     */
    Class<?> getRepositoryBaseClass();
}
