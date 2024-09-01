package com.ziyao.ideal.generator.settings;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.generator.core.Naming;
import com.ziyao.ideal.generator.core.OutputType;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class PackageSettings {

    /**
     * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     */
    private String parent = "com.ziyao.ideal";

    /**
     * 父包模块名
     */
    private String moduleName = "";
    /**
     * /**
     * Entity包名
     */
    private String entity = "domain.entity";
    /**
     * dto包名
     */
    private String dto = "domain.dto";
    /**
     * 仓库包名
     */
    private String repository = "repository.jpa";

    /**
     * Mapper包名
     */
    private String mapper = "repository.mapper";

    /**
     * Service包名
     */
    private String service = "service";

    /**
     * Service Impl包名
     */
    private String serviceImpl = "service.impl";

    /**
     * Mapper XML包名
     */
    private String xml = "resources/mapper/auto";

    /**
     * Controller包名
     */
    private String controller = "controllers";
    /**
     * 包输出路径
     */
    private final Map<OutputType, String> outputPath = new HashMap<>();
    /**
     * 包配置信息
     */
    private final Map<String, String> packages = new HashMap<>();

    public String getParentReference() {
        if (Strings.hasText(moduleName)) {
            return parent + Strings.DOT + moduleName;
        }
        return parent;
    }

    /**
     * 连接包名
     *
     * @param subPackage 包路径
     * @return 返回包路径
     */
    public String joinPackage(String subPackage) {
        String parent = getParentReference();
        return Strings.isEmpty(parent) ? subPackage : (parent + Strings.DOT + subPackage);
    }

    public String getPackage(String module) {
        return getPackages().get(module);
    }

    public String getPackage(Naming naming) {
        return getPackages().get(naming.name());
    }

    public Map<String, String> getPackages() {
        initPackages();
        return packages;
    }

    private void initPackages() {
        if (Collections.isEmpty(packages)) {
            packages.put(Naming.ModuleName.name(), this.getModuleName());
            packages.put(Naming.Entity.name(), this.joinPackage(this.getEntity()));
            packages.put(Naming.Dto.name(), this.joinPackage(this.getDto()));
            packages.put(Naming.Repository.name(), this.joinPackage(this.getRepository()));
            packages.put(Naming.Mapper.name(), this.joinPackage(this.getMapper()));
            packages.put(Naming.Xml.name(), this.joinPackage(this.getXml()));
            packages.put(Naming.Service.name(), this.joinPackage(this.getService()));
            packages.put(Naming.ServiceImpl.name(), this.joinPackage(this.getServiceImpl()));
            packages.put(Naming.Controller.name(), this.joinPackage(this.getController()));
            packages.put(Naming.Parent.name(), this.getParent());
        }
    }

    public static class Builder {
        private final PackageSettings packageSettings;

        public Builder() {
            this.packageSettings = new PackageSettings();
        }

        public Builder parent(String parent) {
            this.packageSettings.parent = parent;
            return this;
        }

        public Builder moduleName(String moduleName) {
            this.packageSettings.moduleName = moduleName;
            return this;
        }

        public Builder entity(String entity) {
            this.packageSettings.entity = entity;
            return this;
        }

        public Builder dto(String dto) {
            this.packageSettings.dto = dto;
            return this;
        }

        public Builder repository(String repository) {
            this.packageSettings.repository = repository;
            return this;
        }

        public Builder service(String service) {
            this.packageSettings.service = service;
            return this;
        }

        public Builder serviceImpl(String serviceImpl) {
            this.packageSettings.serviceImpl = serviceImpl;
            return this;
        }

        public Builder mapper(String mapper) {
            this.packageSettings.mapper = mapper;
            return this;
        }

        public Builder xml(String xml) {
            this.packageSettings.xml = xml;
            return this;
        }

        public Builder controller(String controller) {
            this.packageSettings.controller = controller;
            return this;
        }

        public Builder outputPath(Consumer<Map<OutputType, String>> consumer) {
            consumer.accept(this.packageSettings.outputPath);
            return this;
        }

        public PackageSettings build() {
            return packageSettings;
        }
    }
}
