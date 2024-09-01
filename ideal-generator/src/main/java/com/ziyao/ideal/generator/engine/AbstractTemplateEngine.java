package com.ziyao.ideal.generator.engine;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.core.lang.Nullable;
import com.ziyao.ideal.generator.ConfigSettings;
import com.ziyao.ideal.generator.core.OutputType;
import com.ziyao.ideal.generator.core.PersistType;
import com.ziyao.ideal.generator.core.meta.TemplateContext;
import com.ziyao.ideal.generator.settings.GlobalSettings;
import com.ziyao.ideal.generator.settings.StrategySettings;
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
    private ConfigSettings configSettings;

    /**
     * 模板引擎初始化
     */
    @NonNull
    public abstract AbstractTemplateEngine init(@NonNull ConfigSettings configSettings);


    /**
     * 输出实体文件
     *
     * @param templateContext 表信息
     * @param objectMap       渲染数据
     */
    protected void outputEntity(@NonNull TemplateContext templateContext, @NonNull Map<String, Object> objectMap) {
        String entityName = templateContext.getEntityName();
        String entityPath = getOutput(OutputType.entity);
        String dtoPath = getOutput(OutputType.dto);
        PersistentTemplate persistent = this.configSettings.getStrategySettings().persistent();
        if (persistent.isGenerate()) {
            String entityFile = String.format((entityPath + File.separator + "%s" + suffixJava()), entityName);
            outputFile(getOutputFile(entityFile, OutputType.entity), objectMap, templateFilePath(persistent.getTemplate()), persistent.isOverride());

            String dtoFile = String.format((dtoPath + File.separator + templateContext.getDtoName() + suffixJava()), entityName);
            outputFile(getOutputFile(dtoFile, OutputType.dto), objectMap,
                    templateFilePath(persistent.getDtoTemplate()), persistent.isOverride());
        }

    }

    protected File getOutputFile(String filePath, OutputType outputType) {
        return this.configSettings.getStrategySettings().getOutputFile().create(filePath, outputType);
    }

    /**
     * 输出repository文件
     *
     * @param context   表信息
     * @param objectMap 渲染数据
     */
    protected void outputRepository(TemplateContext context, Map<String, Object> objectMap) {
        String entityName = context.getEntityName();
        String repositoryPath = getOutput(OutputType.repository);
        RepositoryTemplate repository = this.configSettings.getStrategySettings().repository();
        String repositoryName = context.getRepositoryName();
        String mapperName = context.getMapperName();
        if (repository.isGenerate()) {
            String name = PersistType.JPA.equals(configSettings.getGlobalSettings().getPersistType()) ? repositoryName : mapperName;
            String mapperFile = String.format((repositoryPath + File.separator + name + suffixJava()), entityName);
            outputFile(getOutputFile(mapperFile, OutputType.repository), objectMap,
                    templateFilePath(repository.getTemplate()), repository.isOverride());

            switch (configSettings.getGlobalSettings().getPersistType()) {
                case MYBATIS_PLUS, TK_MYBATIS -> {
                    String xmlPath = getOutput(OutputType.xml);
                    String xmlFile = String.format((xmlPath + File.separator + mapperName + ".xml"), entityName);
                    outputFile(getOutputFile(xmlFile, OutputType.xml), objectMap,
                            templateFilePath(repository.getXmlTemplate()), repository.isOverride());
                }
            }

        }
    }

    /**
     * 输出service文件
     *
     * @param templateContext 表信息
     * @param objectMap       渲染数据
     */
    protected void outputService(@NonNull TemplateContext templateContext, @NonNull Map<String, Object> objectMap) {
        // IMpService.java
        String entityName = templateContext.getEntityName();
        // 判断是否要生成service接口
        ServiceTemplate service = this.configSettings.getStrategySettings().service();
        if (service.isGenerate()) {
            String servicePath = getOutput(OutputType.service);
            String serviceFile = String.format((servicePath + File.separator + templateContext.getServiceName() + suffixJava()), entityName);
            outputFile(getOutputFile(serviceFile, OutputType.service), objectMap,
                    templateFilePath(service.getTemplate()), service.isOverride());
        }
        // MpServiceImpl.java
        String serviceImplPath = getOutput(OutputType.serviceImpl);

        String implFile = String.format((serviceImplPath + File.separator + templateContext.getServiceImplName() + suffixJava()), entityName);
        outputFile(getOutputFile(implFile, OutputType.serviceImpl), objectMap,
                templateFilePath(service.getImplTemplate()), service.isOverride());

    }

    /**
     * 输出controller文件
     *
     * @param templateContext 表信息
     * @param objectMap       渲染数据
     */
    protected void outputController(@NonNull TemplateContext templateContext, @NonNull Map<String, Object> objectMap) {
        // MpController.java
        ControllerTemplate controller = this.configSettings.getStrategySettings().controller();
        String controllerPath = getOutput(OutputType.controller);
        if (controller.isGenerate()) {
            String entityName = templateContext.getEntityName();
            String controllerFile = String.format((controllerPath + File.separator + templateContext.getControllerName() + suffixJava()), entityName);
            outputFile(getOutputFile(controllerFile, OutputType.controller), objectMap,
                    templateFilePath(controller.getTemplate()), this.configSettings.getStrategySettings().controller().isOverride());
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
    protected String getOutput(@NonNull OutputType outputType) {
        return this.configSettings.getOutputPackages().get(outputType);
    }

    /**
     * 批量输出 java xml 文件
     */
    @NonNull
    public AbstractTemplateEngine batchOutput() {
        try {
            List<TemplateContext> templateContextList = configSettings.getTemplateContexts();
            templateContextList.forEach(metaInfo -> {
                Map<String, Object> objectMap = this.getObjectMap(configSettings, metaInfo);

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
        String outDir = this.configSettings.getGlobalSettings().getOutputDir();
        if (Strings.isEmpty(outDir) || !new File(outDir).exists()) {
            System.err.println("未找到输出目录：" + outDir);
        } else if (this.configSettings.getGlobalSettings().isOpen()) {
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
     * @param config  配置信息
     * @param context 表信息对象
     * @return ignore
     */
    @NonNull
    public Map<String, Object> getObjectMap(@NonNull ConfigSettings config, @NonNull TemplateContext context) {
        StrategySettings strategySettings = config.getStrategySettings();
        Map<String, Object> metadata = new HashMap<>();
        metadata.putAll(strategySettings.persistent().load(context));
        metadata.putAll(strategySettings.repository().load(context));
        metadata.putAll(strategySettings.service().load(context));
        metadata.putAll(strategySettings.controller().load(context));

        // 其他渲染信息
        metadata.put("config", config);
        metadata.put("package", config.getPackageSettings().getPackages());
        GlobalSettings globalSettings = config.getGlobalSettings();
        metadata.put("author", globalSettings.getAuthor());
        metadata.put("swagger", globalSettings.isSwagger());
        metadata.put("springdoc", globalSettings.isSpringdoc());
        metadata.put("date", globalSettings.getCommentDate());
        metadata.put("persistType", globalSettings.getPersistType().getType());
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
        metadata.put("context", context);
        return metadata;
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
