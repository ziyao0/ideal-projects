package com.ziyao.ideal.generator.engine;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.core.lang.Nullable;
import com.ziyao.ideal.generator.ConfigManager;
import com.ziyao.ideal.generator.core.OutputType;
import com.ziyao.ideal.generator.core.metadata.MetaInfo;
import com.ziyao.ideal.generator.properties.GlobalProperties;
import com.ziyao.ideal.generator.properties.StrategyProperties;
import com.ziyao.ideal.generator.template.ControllerTemplate;
import com.ziyao.ideal.generator.template.PersistentTemplate;
import com.ziyao.ideal.generator.template.RepositoryTemplate;
import com.ziyao.ideal.generator.template.ServiceTemplate;
import com.ziyao.ideal.generator.utils.FileUtils;
import com.ziyao.ideal.generator.utils.RuntimeUtils;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public abstract class AbstractTemplateEngine {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * 配置信息
     */
    @Setter
    private ConfigManager configManager;

    /**
     * 模板引擎初始化
     */
    @NonNull
    public abstract AbstractTemplateEngine init(@NonNull ConfigManager configManager);


    /**
     * 输出实体文件
     *
     * @param metaInfo  表信息
     * @param objectMap 渲染数据
     */
    protected void outputEntity(@NonNull MetaInfo metaInfo, @NonNull Map<String, Object> objectMap) {
        String entityName = metaInfo.getEntityName();
        String entityPath = getPathInfo(OutputType.entity);
        String dtoPath = getPathInfo(OutputType.dto);
        PersistentTemplate persistent = this.configManager.getStrategyProperties().persistent();
        if (persistent.isGenerate()) {
            String entityFile = String.format((entityPath + File.separator + "%s" + suffixJava()), entityName);
            outputFile(getOutputFile(entityFile, OutputType.entity), objectMap, templateFilePath(persistent.getTemplate()), persistent.isOverride());

            String dtoFile = String.format((dtoPath + File.separator + metaInfo.getDtoName() + suffixJava()), entityName);
            outputFile(getOutputFile(dtoFile, OutputType.dto), objectMap,
                    templateFilePath(persistent.getDtoTemplate()), persistent.isOverride());
        }

    }

    protected File getOutputFile(String filePath, OutputType outputType) {
        return this.configManager.getStrategyProperties().getOutputFile().createFile(filePath, outputType);
    }

    /**
     * 输出repository文件
     *
     * @param metaInfo  表信息
     * @param objectMap 渲染数据
     */
    protected void outputRepository(MetaInfo metaInfo, Map<String, Object> objectMap) {
        String entityName = metaInfo.getEntityName();
        String repositoryPath = getPathInfo(OutputType.repository);
        RepositoryTemplate repository = this.configManager.getStrategyProperties().repository();
        if (repository.isGenerate()) {
            String mapperFile = String.format((repositoryPath + File.separator + metaInfo.getRepositoryName() + suffixJava()), entityName);
            outputFile(getOutputFile(mapperFile, OutputType.repository), objectMap,
                    templateFilePath(repository.getTemplate()), repository.isOverride());

            switch (configManager.getGlobalProperties().getPersistType()) {
                case MYBATIS_PLUS, TK_MYBATIS -> {
                    String xmlPath = getPathInfo(OutputType.xml);
                    String xmlFile = String.format((xmlPath + File.separator + metaInfo.getRepositoryName() + ".xml"), entityName);
                    outputFile(getOutputFile(xmlFile, OutputType.xml), objectMap,
                            templateFilePath(repository.getXmlTemplate()), repository.isOverride());
                }
            }

        }
    }

    /**
     * 输出service文件
     *
     * @param metaInfo  表信息
     * @param objectMap 渲染数据
     */
    protected void outputService(@NonNull MetaInfo metaInfo, @NonNull Map<String, Object> objectMap) {
        // IMpService.java
        String entityName = metaInfo.getEntityName();
        // 判断是否要生成service接口
        ServiceTemplate service = this.configManager.getStrategyProperties().service();
        if (service.isGenerate()) {
            String servicePath = getPathInfo(OutputType.service);
            String serviceFile = String.format((servicePath + File.separator + metaInfo.getServiceName() + suffixJava()), entityName);
            outputFile(getOutputFile(serviceFile, OutputType.service), objectMap,
                    templateFilePath(service.getTemplate()), service.isOverride());
        }
        // MpServiceImpl.java
        String serviceImplPath = getPathInfo(OutputType.serviceImpl);

        String implFile = String.format((serviceImplPath + File.separator + metaInfo.getServiceImplName() + suffixJava()), entityName);
        outputFile(getOutputFile(implFile, OutputType.serviceImpl), objectMap,
                templateFilePath(service.getImplTemplate()), service.isOverride());

    }

    /**
     * 输出controller文件
     *
     * @param metaInfo  表信息
     * @param objectMap 渲染数据
     */
    protected void outputController(@NonNull MetaInfo metaInfo, @NonNull Map<String, Object> objectMap) {
        // MpController.java
        ControllerTemplate controller = this.configManager.getStrategyProperties().controller();
        String controllerPath = getPathInfo(OutputType.controller);
        if (controller.isGenerate()) {
            String entityName = metaInfo.getEntityName();
            String controllerFile = String.format((controllerPath + File.separator + metaInfo.getControllerName() + suffixJava()), entityName);
            outputFile(getOutputFile(controllerFile, OutputType.controller), objectMap,
                    templateFilePath(controller.getTemplate()), this.configManager.getStrategyProperties().controller().isOverride());
        }
    }

    /**
     * 输出文件
     *
     * @param file         文件
     * @param objectMap    渲染信息
     * @param templatePath 模板路径
     * @param fileOverride 是否覆盖已有文件
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
     * 获取路径信息
     *
     * @param outputType 输出文件
     * @return 路径信息
     */
    @Nullable
    protected String getPathInfo(@NonNull OutputType outputType) {
        return this.configManager.getPathInfo().get(outputType);
    }

    /**
     * 批量输出 java xml 文件
     */
    @NonNull
    public AbstractTemplateEngine batchOutput() {
        try {
            List<MetaInfo> metaInfoList = configManager.getMetaInfos();
            metaInfoList.forEach(metaInfo -> {
                Map<String, Object> objectMap = this.getObjectMap(configManager, metaInfo);

                // entity and dto
                outputEntity(metaInfo, objectMap);
                // repository and xml
                outputRepository(metaInfo, objectMap);
                // service
                outputService(metaInfo, objectMap);
                // controller
                outputController(metaInfo, objectMap);
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
        String outDir = this.configManager.getGlobalProperties().getOutputDir();
        if (Strings.isEmpty(outDir) || !new File(outDir).exists()) {
            System.err.println("未找到输出目录：" + outDir);
        } else if (this.configManager.getGlobalProperties().isOpen()) {
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
     * @param config   配置信息
     * @param metaInfo 表信息对象
     * @return ignore
     */
    @NonNull
    public Map<String, Object> getObjectMap(@NonNull ConfigManager config, @NonNull MetaInfo metaInfo) {
        StrategyProperties strategyProperties = config.getStrategyProperties();
        Map<String, Object> controllerData = strategyProperties.controller().load(metaInfo);
        Map<String, Object> objectMap = new HashMap<>(controllerData);
        Map<String, Object> serviceData = strategyProperties.service().load(metaInfo);
        objectMap.putAll(serviceData);
        Map<String, Object> entityData = strategyProperties.persistent().load(metaInfo);
        objectMap.putAll(entityData);
        objectMap.put("config", config);
        objectMap.put("package", config.getPackageProperties().getPackages());
        GlobalProperties globalProperties = config.getGlobalProperties();
        objectMap.put("author", globalProperties.getAuthor());
        objectMap.put("swagger", globalProperties.isSwagger());
        objectMap.put("springdoc", globalProperties.isSpringdoc());
        objectMap.put("date", globalProperties.getCommentDate());
        objectMap.put("persistType", globalProperties.getPersistType());
//        // 启用 schema 处理逻辑
//        String schemaName = "";
//        if (strategyProperties.isEnableSchema()) {
//            // 存在 schemaName 设置拼接 . 组合表名
//            schemaName = config.getDatabaseProperties().getSchemaName();
//            if (Strings.hasText(schemaName)) {
//                schemaName += ".";
//                metaInfo.setConvert(true);
//            }
//        }
//        objectMap.put("schemaName", schemaName);
        objectMap.put("table", metaInfo);
        objectMap.put("entity", metaInfo.getEntityName());
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
    protected String suffixJava() {
        return ".java";
    }

}
