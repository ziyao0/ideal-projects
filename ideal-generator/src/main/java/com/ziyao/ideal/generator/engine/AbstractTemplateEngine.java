/*
 * Copyright (c) 2011-2024, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ziyao.ideal.generator.engine;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.core.lang.Nullable;
import com.ziyao.ideal.core.text.StrPool;
import com.ziyao.ideal.generator.config.*;
import com.ziyao.ideal.generator.config.builder.*;
import com.ziyao.ideal.generator.config.po.TableInfo;
import com.ziyao.ideal.generator.util.FileUtils;
import com.ziyao.ideal.generator.util.RuntimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;


/**
 * 模板引擎抽象类
 *
 * @author hubin
 * @since 2018-01-10
 */
public abstract class AbstractTemplateEngine {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * 配置信息
     */
    private ConfigBuilder configBuilder;

    /**
     * 模板引擎初始化
     */
    @NonNull
    public abstract AbstractTemplateEngine init(@NonNull ConfigBuilder configBuilder);

    /**
     * 输出自定义模板文件
     *
     * @param customFiles 自定义模板文件列表
     * @param tableInfo   表信息
     * @param objectMap   渲染数据
     * @since 3.5.3
     */
    protected void outputCustomFile(@NonNull List<CustomFile> customFiles, @NonNull TableInfo tableInfo, @NonNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String parentPath = getPathInfo(OutputFile.parent);
        customFiles.forEach(file -> {
            String filePath = Strings.hasLength(file.getFilePath()) ? file.getFilePath() : parentPath;
            if (Strings.hasLength(file.getPackageName())) {
                filePath = filePath + File.separator + file.getPackageName().replaceAll("\\.", StrPool.BACKSLASH + File.separator);
            }
            Function<TableInfo, String> formatNameFunction = file.getFormatNameFunction();
            String fileName = filePath + File.separator + (null != formatNameFunction ? formatNameFunction.apply(tableInfo) : entityName) + file.getFileName();
            outputFile(new File(fileName), objectMap, file.getTemplatePath(), file.isFileOverride());
        });
    }

    /**
     * 输出实体文件
     *
     * @param tableInfo 表信息
     * @param objectMap 渲染数据
     */
    protected void outputEntity(@NonNull TableInfo tableInfo, @NonNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String entityPath = getPathInfo(OutputFile.entity);
        Entity entity = this.getConfigBuilder().getStrategyConfig().entity();
        GlobalConfig globalConfig = configBuilder.getGlobalConfig();
        if (entity.isGenerate()) {
            String entityFile = String.format((entityPath + File.separator + "%s" + suffixJavaOrKt()), entityName);
            outputFile(getOutputFile(entityFile, OutputFile.entity), objectMap, templateFilePath(globalConfig.isKotlin() ? entity.getKotlinTemplate() : entity.getJavaTemplate()), getConfigBuilder().getStrategyConfig().entity().isFileOverride());
        }
    }

    protected File getOutputFile(String filePath, OutputFile outputFile) {
        return getConfigBuilder().getStrategyConfig().getOutputFile().createFile(filePath, outputFile);
    }

    /**
     * 输出Mapper文件(含xml)
     *
     * @param tableInfo 表信息
     * @param objectMap 渲染数据
     */
    protected void outputMapper(@NonNull TableInfo tableInfo, @NonNull Map<String, Object> objectMap) {
        // MpMapper.java
        String entityName = tableInfo.getEntityName();
        String mapperPath = getPathInfo(OutputFile.mapper);
        Mapper mapper = this.getConfigBuilder().getStrategyConfig().mapper();
        if (mapper.isGenerateMapper()) {
            String mapperFile = String.format((mapperPath + File.separator + tableInfo.getMapperName() + suffixJavaOrKt()), entityName);
            outputFile(getOutputFile(mapperFile, OutputFile.mapper), objectMap, templateFilePath(mapper.getMapperTemplatePath()), getConfigBuilder().getStrategyConfig().mapper().isFileOverride());
        }
        // MpMapper.xml
        String xmlPath = getPathInfo(OutputFile.xml);
        if (mapper.isGenerateMapperXml()) {
            String xmlFile = String.format((xmlPath + File.separator + tableInfo.getXmlName() + ConstVal.XML_SUFFIX), entityName);
            outputFile(getOutputFile(xmlFile, OutputFile.xml), objectMap, templateFilePath(mapper.getMapperXmlTemplatePath()), getConfigBuilder().getStrategyConfig().mapper().isFileOverride());
        }
    }

    /**
     * 输出service文件
     *
     * @param tableInfo 表信息
     * @param objectMap 渲染数据
     */
    protected void outputService(@NonNull TableInfo tableInfo, @NonNull Map<String, Object> objectMap) {
        // IMpService.java
        String entityName = tableInfo.getEntityName();
        // 判断是否要生成service接口
        Service service = this.getConfigBuilder().getStrategyConfig().service();
        if (service.isGenerateService()) {
            String servicePath = getPathInfo(OutputFile.service);
            String serviceFile = String.format((servicePath + File.separator + tableInfo.getServiceName() + suffixJavaOrKt()), entityName);
            outputFile(getOutputFile(serviceFile, OutputFile.service), objectMap, templateFilePath(service.getServiceTemplate()), getConfigBuilder().getStrategyConfig().service().isFileOverride());
        }
        // MpServiceImpl.java
        String serviceImplPath = getPathInfo(OutputFile.serviceImpl);
        if (service.isGenerateServiceImpl()) {
            String implFile = String.format((serviceImplPath + File.separator + tableInfo.getServiceImplName() + suffixJavaOrKt()), entityName);
            outputFile(getOutputFile(implFile, OutputFile.serviceImpl), objectMap, templateFilePath(service.getServiceImplTemplate()), getConfigBuilder().getStrategyConfig().service().isFileOverride());
        }
    }

    /**
     * 输出controller文件
     *
     * @param tableInfo 表信息
     * @param objectMap 渲染数据
     */
    protected void outputController(@NonNull TableInfo tableInfo, @NonNull Map<String, Object> objectMap) {
        // MpController.java
        Controller controller = this.getConfigBuilder().getStrategyConfig().controller();
        String controllerPath = getPathInfo(OutputFile.controller);
        if (controller.isGenerate()) {
            String entityName = tableInfo.getEntityName();
            String controllerFile = String.format((controllerPath + File.separator + tableInfo.getControllerName() + suffixJavaOrKt()), entityName);
            outputFile(getOutputFile(controllerFile, OutputFile.controller), objectMap, templateFilePath(controller.getTemplatePath()), getConfigBuilder().getStrategyConfig().controller().isFileOverride());
        }
    }

    /**
     * 输出文件
     *
     * @param file         文件
     * @param objectMap    渲染信息
     * @param templatePath 模板路径
     * @param fileOverride 是否覆盖已有文件
     * @since 3.5.2
     */
    protected void outputFile(@NonNull File file, @NonNull Map<String, Object> objectMap, @NonNull String templatePath, boolean fileOverride) {
        if (isCreate(file, fileOverride)) {
            try {
                // 全局判断【默认】
                boolean exist = file.exists();
                if (!exist) {
                    File parentFile = file.getParentFile();
                    FileUtils.forceMkdir(parentFile);
                }
                writer(objectMap, templatePath, file);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    /**
     * 获取模板路径
     *
     * @param function function
     * @return 模板路径
     * @deprecated 3.5.6
     */
    @NonNull
    @Deprecated
    protected Optional<String> getTemplateFilePath(@NonNull Function<TemplateConfig, String> function) {
        TemplateConfig templateConfig = getConfigBuilder().getTemplateConfig();
        String filePath = function.apply(templateConfig);
        if (Strings.hasLength(filePath)) {
            return Optional.of(templateFilePath(filePath));
        }
        return Optional.empty();
    }

    /**
     * 获取路径信息
     *
     * @param outputFile 输出文件
     * @return 路径信息
     */
    @Nullable
    protected String getPathInfo(@NonNull OutputFile outputFile) {
        return getConfigBuilder().getPathInfo().get(outputFile);
    }

    /**
     * 批量输出 java xml 文件
     */
    @NonNull
    public AbstractTemplateEngine batchOutput() {
        try {
            ConfigBuilder config = this.getConfigBuilder();
            List<TableInfo> tableInfoList = config.getTableInfoList();
            tableInfoList.forEach(tableInfo -> {
                Map<String, Object> objectMap = this.getObjectMap(config, tableInfo);
                Optional.ofNullable(config.getInjectionConfig()).ifPresent(t -> {
                    // 添加自定义属性
                    t.beforeOutputFile(tableInfo, objectMap);
                    // 输出自定义文件
                    outputCustomFile(t.getCustomFiles(), tableInfo, objectMap);
                });
                // entity
                outputEntity(tableInfo, objectMap);
                // mapper and xml
                outputMapper(tableInfo, objectMap);
                // service
                outputService(tableInfo, objectMap);
                // controller
                outputController(tableInfo, objectMap);
            });
        } catch (Exception e) {
            throw new RuntimeException("无法创建文件，请检查配置信息！", e);
        }
        return this;
    }

    /**
     * 将模板转化成为文件
     *
     * @param objectMap    渲染对象 MAP 信息
     * @param templatePath 模板文件
     * @param outputFile   文件生成的目录
     * @throws Exception 异常
     */
    public abstract void writer(@NonNull Map<String, Object> objectMap, @NonNull String templatePath, @NonNull File outputFile) throws Exception;

    /**
     * 打开输出目录
     */
    public void open() {
        String outDir = getConfigBuilder().getGlobalConfig().getOutputDir();
        if (Strings.isEmpty(outDir) || !new File(outDir).exists()) {
            System.err.println("未找到输出目录：" + outDir);
        } else if (getConfigBuilder().getGlobalConfig().isOpen()) {
            try {
                RuntimeUtils.openDir(outDir);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 渲染对象 MAP 信息
     *
     * @param config    配置信息
     * @param tableInfo 表信息对象
     * @return ignore
     */
    @NonNull
    public Map<String, Object> getObjectMap(@NonNull ConfigBuilder config, @NonNull TableInfo tableInfo) {
        StrategyConfig strategyConfig = config.getStrategyConfig();
        Map<String, Object> controllerData = strategyConfig.controller().renderData(tableInfo);
        Map<String, Object> objectMap = new HashMap<>(controllerData);
        Map<String, Object> mapperData = strategyConfig.mapper().renderData(tableInfo);
        objectMap.putAll(mapperData);
        Map<String, Object> serviceData = strategyConfig.service().renderData(tableInfo);
        objectMap.putAll(serviceData);
        Map<String, Object> entityData = strategyConfig.entity().renderData(tableInfo);
        objectMap.putAll(entityData);
        objectMap.put("config", config);
        objectMap.put("package", config.getPackageConfig().getPackageInfo());
        GlobalConfig globalConfig = config.getGlobalConfig();
        objectMap.put("author", globalConfig.getAuthor());
        objectMap.put("kotlin", globalConfig.isKotlin());
        objectMap.put("swagger", globalConfig.isSwagger());
        objectMap.put("springdoc", globalConfig.isSpringdoc());
        objectMap.put("date", globalConfig.getCommentDate());
        // 启用 schema 处理逻辑
        String schemaName = "";
        if (strategyConfig.isEnableSchema()) {
            // 存在 schemaName 设置拼接 . 组合表名
            schemaName = config.getDataSourceConfig().getSchemaName();
            if (Strings.hasLength(schemaName)) {
                schemaName += ".";
                tableInfo.setConvert(true);
            }
        }
        objectMap.put("schemaName", schemaName);
        objectMap.put("table", tableInfo);
        objectMap.put("entity", tableInfo.getEntityName());
        return objectMap;
    }

    /**
     * 模板真实文件路径
     *
     * @param filePath 文件路径
     * @return ignore
     */
    @NonNull
    public abstract String templateFilePath(@NonNull String filePath);

    /**
     * 检查文件是否创建文件
     *
     * @param file         文件
     * @param fileOverride 是否覆盖已有文件
     * @return 是否创建文件
     * @since 3.5.2
     */
    protected boolean isCreate(@NonNull File file, boolean fileOverride) {
        if (file.exists() && !fileOverride) {
            LOGGER.warn("文件[{}]已存在，且未开启文件覆盖配置，需要开启配置可到策略配置中设置！！！", file.getName());
        }
        return !file.exists() || fileOverride;
    }

    /**
     * 文件后缀
     */
    protected String suffixJavaOrKt() {
        return getConfigBuilder().getGlobalConfig().isKotlin() ? ConstVal.KT_SUFFIX : ConstVal.JAVA_SUFFIX;
    }

    @NonNull
    public ConfigBuilder getConfigBuilder() {
        return configBuilder;
    }

    @NonNull
    public void setConfigBuilder(@NonNull ConfigBuilder configBuilder) {
        this.configBuilder = configBuilder;
    }
}
