package com.ziyao.ideal.generator.config;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.core.NameTemplate;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 包相关的配置项
 */
@Getter
public class PackageConfig {

    private PackageConfig() {
    }

    /**
     * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     */
    private String parent = "com.ziyao.ideal";

    /**
     * 父包模块名
     */

    private String moduleName = "";

    /**
     * Entity包名
     */

    private String entity = "domain.entity";

    private String dto = "domain.dto";

    private String repository = "repository.jpa";

    /**
     * Service包名
     */

    private String service = "service";

    /**
     * Service Impl包名
     */

    private String serviceImpl = "service.impl";

    /**
     * Mapper包名
     */

    private String mapper = "repository.mapper";

    /**
     * Mapper XML包名
     */

    private String xml = "mapper.xml";

    /**
     * Controller包名
     */

    private String controller = "controllers";

    /**
     * 路径配置信息
     */
    private Map<OutputFile, String> pathInfo;

    /**
     * 包配置信息
     */
    private final Map<String, String> packageInfo = new HashMap<>();

    /**
     * 父包名
     */
    @NonNull
    public String getParent() {
        if (Strings.hasLength(moduleName)) {
            return parent + Strings.DOT + moduleName;
        }
        return parent;
    }

    /**
     * 连接父子包名
     *
     * @param subPackage 子包名
     * @return 连接后的包名
     */
    @NonNull
    public String joinPackage(String subPackage) {
        String parent = getParent();
        return Strings.isEmpty(parent) ? subPackage : (parent + Strings.DOT + subPackage);
    }

    /**
     * 获取包配置信息
     *
     * @return 包配置信息
     */
    @NonNull
    public Map<String, String> getPackageInfo() {
        if (packageInfo.isEmpty()) {
            packageInfo.put(ConstVal.MODULE_NAME, this.getModuleName());
            packageInfo.put(NameTemplate.Entity.name(), this.joinPackage(this.getEntity()));
            packageInfo.put(NameTemplate.Dto.name(), this.joinPackage(this.getDto()));
            packageInfo.put(NameTemplate.Repository.name(), this.joinPackage(this.getRepository()));
            packageInfo.put(NameTemplate.Mapper.name(), this.joinPackage(this.getMapper()));
            packageInfo.put(ConstVal.XML, this.joinPackage(this.getXml()));
            packageInfo.put(NameTemplate.Service.name(), this.joinPackage(this.getService()));
            packageInfo.put(NameTemplate.ServiceImpl.name(), this.joinPackage(this.getServiceImpl()));
            packageInfo.put(NameTemplate.Controller.name(), this.joinPackage(this.getController()));
            packageInfo.put(ConstVal.PARENT, this.getParent());
        }
        return Collections.unmodifiableMap(this.packageInfo);
    }

    /**
     * 获取包配置信息
     *
     * @param module 模块
     * @return 配置信息
     */
    public String getPackageInfo(String module) {
        return getPackageInfo().get(module);
    }

    /**
     * 构建者
     */
    public static class Builder implements IConfigBuilder<PackageConfig> {

        private final PackageConfig packageConfig;

        public Builder() {
            this.packageConfig = new PackageConfig();
        }

        public Builder(@NonNull String parent, @NonNull String moduleName) {
            this();
            this.packageConfig.parent = parent;
            this.packageConfig.moduleName = moduleName;
        }

        /**
         * 指定父包名
         *
         * @param parent 父包名
         * @return this
         */
        public Builder parent(@NonNull String parent) {
            this.packageConfig.parent = parent;
            return this;
        }

        /**
         * 指定模块名称
         *
         * @param moduleName 模块名
         * @return this
         */
        public Builder moduleName(@NonNull String moduleName) {
            this.packageConfig.moduleName = moduleName;
            return this;
        }

        /**
         * 指定实体包名
         *
         * @param entity 实体包名
         * @return this
         */
        public Builder entity(@NonNull String entity) {
            this.packageConfig.entity = entity;
            return this;
        }

        /**
         * 指定实体包名
         *
         * @param dto dto包名
         * @return this
         */
        public Builder dto(@NonNull String dto) {
            this.packageConfig.dto = dto;
            return this;
        }

        /**
         * 指定repository包名
         *
         * @param repository repository包名
         * @return this
         */
        public Builder repository(@NonNull String repository) {
            this.packageConfig.repository = repository;
            return this;
        }

        /**
         * 指定service接口包名
         *
         * @param service service包名
         * @return this
         */
        public Builder service(@NonNull String service) {
            this.packageConfig.service = service;
            return this;
        }

        /**
         * service实现类包名
         *
         * @param serviceImpl service实现类包名
         * @return this
         */
        public Builder serviceImpl(@NonNull String serviceImpl) {
            this.packageConfig.serviceImpl = serviceImpl;
            return this;
        }

        /**
         * 指定mapper接口包名
         *
         * @param mapper mapper包名
         * @return this
         */
        public Builder mapper(@NonNull String mapper) {
            this.packageConfig.mapper = mapper;
            return this;
        }

        /**
         * 指定xml包名
         *
         * @param xml xml包名
         * @return this
         */
        public Builder xml(@NonNull String xml) {
            this.packageConfig.xml = xml;
            return this;
        }

        /**
         * 指定控制器包名
         *
         * @param controller 控制器包名
         * @return this
         */
        public Builder controller(@NonNull String controller) {
            this.packageConfig.controller = controller;
            return this;
        }

        /**
         * 路径配置信息
         *
         * @param pathInfo 路径配置信息
         * @return this
         */
        public Builder pathInfo(@NonNull Map<OutputFile, String> pathInfo) {
            this.packageConfig.pathInfo = pathInfo;
            return this;
        }

        /**
         * 连接父子包名
         *
         * @param subPackage 子包名
         * @return 连接后的包名
         */
        @NonNull
        public String joinPackage(@NonNull String subPackage) {
            return this.packageConfig.joinPackage(subPackage);
        }

        /**
         * 构建包配置对象
         * <p>当指定{@link #parent(String)} 与 {@link #moduleName(String)}时,其他模块名字会加上这两个作为前缀</p>
         * <p>
         * 例如:
         * <p>当设置 {@link #parent(String)},那么entity的配置为 {@link #getParent()}.{@link #getEntity()}</p>
         * <p>当设置 {@link #parent(String)}与{@link #moduleName(String)},那么entity的配置为 {@link #getParent()}.{@link #getModuleName()}.{@link #getEntity()} </p>
         * </p>
         *
         * @return 包配置对象
         */
        @Override
        public PackageConfig build() {
            return this.packageConfig;
        }
    }
}
