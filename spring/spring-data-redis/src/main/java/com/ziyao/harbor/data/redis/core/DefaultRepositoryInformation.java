package com.ziyao.harbor.data.redis.core;

import org.springframework.data.util.TypeInformation;
import org.springframework.util.Assert;

/**
 * @author ziyao zhang
 */
public class DefaultRepositoryInformation implements RepositoryInformation {

    private final RepositoryMetadata metadata;
    private final Class<?> repositoryBaseClass;

    public DefaultRepositoryInformation(RepositoryMetadata metadata,
                                        Class<?> repositoryBaseClass) {
        Assert.notNull(metadata, "Repository metadata must not be null");
        Assert.notNull(repositoryBaseClass, "Repository base class must not be null");

        this.metadata = metadata;
        this.repositoryBaseClass = repositoryBaseClass;
    }


    @Override
    public Class<?> getRepositoryInterface() {
        return metadata.getRepositoryInterface();
    }

    @Override
    public TypeInformation<?> getIdTypeInformation() {
        return metadata.getIdTypeInformation();
    }

    @Override
    public TypeInformation<?> getJavaTypeInformation() {
        return metadata.getJavaTypeInformation();
    }

    @Override
    public Class<?> getRepositoryBaseClass() {
        return this.repositoryBaseClass;
    }
}
