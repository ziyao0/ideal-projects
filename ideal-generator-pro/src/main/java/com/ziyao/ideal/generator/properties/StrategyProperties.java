package com.ziyao.ideal.generator.properties;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.core.OutputFileCreator;
import com.ziyao.ideal.generator.template.*;
import lombok.Getter;

import java.io.File;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class StrategyProperties {

    private StrategyProperties() {
    }

    private PersistentTemplate persistent;
    private final PersistentTemplate.Builder persistentBuilder = new PersistentTemplate.Builder(this);
    private RepositoryTemplate repository;
    private final RepositoryTemplate.Builder repositoryBuilder = new RepositoryTemplate.Builder(this);
    private ServiceTemplate service;
    private final ServiceTemplate.Builder serviceBuilder = new ServiceTemplate.Builder(this);
    private ControllerTemplate controller;
    private final ControllerTemplate.Builder controllerBuilder = new ControllerTemplate.Builder(this);
    @Getter
    private OutputFileCreator outputFile = (path, ot) -> new File(path);


    @NonNull
    public PersistentTemplate persistent() {
        if (this.persistent == null) {
            this.persistent = persistentBuilder.get();
        }
        return this.persistent;
    }

    public PersistentTemplate.Builder persistentBuilder() {
        return this.persistentBuilder;
    }

    public RepositoryTemplate repository() {
        if (this.repository == null) {
            this.repository = repositoryBuilder.get();
        }
        return this.repository;
    }

    public RepositoryTemplate.Builder repositoryBuilder() {
        return this.repositoryBuilder;
    }

    public ServiceTemplate service() {
        if (this.service == null) {
            this.service = serviceBuilder.get();
        }
        return this.service;
    }

    public ServiceTemplate.Builder serviceBuilder() {
        return this.serviceBuilder;
    }

    public ControllerTemplate controller() {
        if (this.controller == null) {
            this.controller = controllerBuilder.get();
        }
        return this.controller;
    }

    public ControllerTemplate.Builder controllerBuilder() {
        return this.controllerBuilder;
    }

    public static class Builder extends AbstractTemplate.AbstractTemplateBuilder {
        private final StrategyProperties strategyConfig;

        public Builder() {
            super(new StrategyProperties());
            strategyConfig = super.build();
        }

        public Builder outputFile(@NonNull OutputFileCreator outputFile) {
            this.strategyConfig.outputFile = outputFile;
            return this;
        }

        public StrategyProperties build() {
            return strategyConfig;
        }
    }
}
