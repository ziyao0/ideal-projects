package com.ziyao.ideal.generator.utils;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.text.StrPool;
import com.ziyao.ideal.generator.core.Naming;
import com.ziyao.ideal.generator.core.OutputType;
import com.ziyao.ideal.generator.properties.GlobalProperties;
import com.ziyao.ideal.generator.properties.PackageProperties;
import com.ziyao.ideal.generator.properties.StrategyProperties;
import com.ziyao.ideal.generator.template.ControllerTemplate;
import com.ziyao.ideal.generator.template.PersistentTemplate;
import com.ziyao.ideal.generator.template.RepositoryTemplate;
import com.ziyao.ideal.generator.template.ServiceTemplate;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public abstract class OutputFileUtils {
    private static final Logger log = LoggerFactory.getLogger(OutputFileUtils.class);

    private static final String JAVA_TMPDIR = "java.io.tmpdir";


    public static Map<OutputType, String> initialize(GlobalProperties globalProperties,
                                                     StrategyProperties strategyProperties,
                                                     PackageProperties packageProperties) {
        Map<OutputType, String> outputDetails = new HashMap<>();
        //初始化文件输出路径
        doInit(globalProperties, strategyProperties, packageProperties, outputDetails);
        Map<OutputType, String> outputPath = packageProperties.getOutputPath();
        if (!Collections.isEmpty(outputPath)) {
            outputDetails.putAll(outputPath);
        }
        return outputPath;
    }

    /**
     * 设置默认输出路径
     *
     * @param globalProperties   全局配置
     * @param strategyProperties 模板配置
     * @param packageProperties  包配置
     * @param outputDetails      输出路径集合
     */
    private static void doInit(GlobalProperties globalProperties, StrategyProperties strategyProperties,
                               PackageProperties packageProperties, Map<OutputType, String> outputDetails) {
        // 实体类相关
        PersistentTemplate persistent = strategyProperties.persistent();
        if (persistent.isGenerate()) {
            // entity
            outputDetails.put(OutputType.entity,
                    joinPath(globalProperties.getOutputDir(), packageProperties.getPackage(Naming.Entity)));
            // dto
            outputDetails.put(OutputType.dto,
                    joinPath(globalProperties.getOutputDir(), packageProperties.getPackage(Naming.Dto)));
        }
        // 持久层
        RepositoryTemplate repository = strategyProperties.repository();
        if (repository.isGenerate()) {
            switch (globalProperties.getPersistType()) {
                // jpa
                case JPA -> outputDetails.put(OutputType.repository,
                        joinPath(globalProperties.getOutputDir(), packageProperties.getPackage(Naming.Repository)));
                // mybatis
                case TK_MYBATIS, MYBATIS_PLUS -> {
                    //
                    outputDetails.put(OutputType.repository,
                            joinPath(globalProperties.getOutputDir(), packageProperties.getPackage(Naming.Mapper)));
                    // mapper xml
                    outputDetails.put(OutputType.xml,
                            joinPath(globalProperties.getOutputDir(), packageProperties.getPackage(Naming.Mapper)));
                }
                default -> log.info("没有匹配的持久化类型！");
            }
        }
        //业务层
        ServiceTemplate service = strategyProperties.service();
        if (service.isGenerate()) {
            outputDetails.put(OutputType.service,
                    joinPath(globalProperties.getOutputDir(), packageProperties.getPackage(Naming.Service)));
            outputDetails.put(OutputType.serviceImpl,
                    joinPath(globalProperties.getOutputDir(), packageProperties.getPackage(Naming.ServiceImpl)));
        }
        // 控制层
        ControllerTemplate controller = strategyProperties.controller();
        if (controller.isGenerate()) {
            outputDetails.put(OutputType.controller,
                    joinPath(globalProperties.getOutputDir(), packageProperties.getPackage(Naming.Controller)));
        }
        outputDetails.put(OutputType.parent, joinPath(globalProperties.getOutputDir(), packageProperties.getPackage(Naming.Parent)));
    }


    /**
     * 连接路径字符串
     *
     * @param parentDir   路径常量字符串
     * @param packageName 包名
     * @return 连接后的路径
     */
    private static String joinPath(String parentDir, String packageName) {
        if (Strings.isEmpty(parentDir)) {
            parentDir = System.getProperty(JAVA_TMPDIR);
        }
        if (!Strings.endsWith(parentDir, File.separator)) {
            parentDir += File.separator;
        }
        packageName = packageName.replaceAll("\\.", StrPool.BACKSLASH + File.separator);
        return parentDir + packageName;
    }
}
