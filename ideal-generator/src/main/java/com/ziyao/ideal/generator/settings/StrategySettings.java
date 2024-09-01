package com.ziyao.ideal.generator.settings;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.core.OutputFileCreator;
import com.ziyao.ideal.generator.template.*;
import lombok.Getter;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class StrategySettings {

    private StrategySettings() {
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
    /**
     * 生成的表名
     */
    @Getter
    private final Set<String> includes = new HashSet<>();
    /**
     * 需要排除生成的表
     */
    @Getter
    private final Set<String> excludes = new HashSet<>();

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
        private final StrategySettings strategySettings;

        public Builder() {
            super(new StrategySettings());
            strategySettings = super.build();
        }

        public Builder outputFile(@NonNull OutputFileCreator outputFile) {
            this.strategySettings.outputFile = outputFile;
            return this;
        }

        public Builder includes(@NonNull String... includes) {
            this.strategySettings.includes.addAll(List.of(includes));
            return this;
        }

        public Builder excludes(@NonNull String... excludes) {
            this.strategySettings.excludes.addAll(List.of(excludes));
            return this;
        }

        public StrategySettings build() {
            return strategySettings;
        }
    }
}
