package com.ziyao.ideal.generator.utils;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.text.StrPool;
import com.ziyao.ideal.generator.core.Naming;
import com.ziyao.ideal.generator.core.OutputType;
import com.ziyao.ideal.generator.settings.GlobalSettings;
import com.ziyao.ideal.generator.settings.PackageSettings;
import com.ziyao.ideal.generator.settings.StrategySettings;
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


    public static Map<OutputType, String> initialize(GlobalSettings globalSettings,
                                                     StrategySettings strategySettings,
                                                     PackageSettings packageSettings) {
        Map<OutputType, String> outputDetails = new HashMap<>();
        //初始化文件输出路径
        doInit(globalSettings, strategySettings, packageSettings, outputDetails);
        Map<OutputType, String> outputPath = packageSettings.getOutputPath();
        if (!Collections.isEmpty(outputPath)) {
            outputDetails.putAll(outputPath);
        }
        return outputDetails;
    }

    /**
     * 设置默认输出路径
     *
     * @param globalSettings   全局配置
     * @param strategySettings 模板配置
     * @param packageSettings  包配置
     * @param outputDetails      输出路径集合
     */
    private static void doInit(GlobalSettings globalSettings, StrategySettings strategySettings,
                               PackageSettings packageSettings, Map<OutputType, String> outputDetails) {
        // 实体类相关
        PersistentTemplate persistent = strategySettings.persistent();
        if (persistent.isGenerate()) {
            // entity
            outputDetails.put(OutputType.entity,
                    joinPath(globalSettings.getOutputDir(), packageSettings.getPackage(Naming.Entity)));
            // dto
            outputDetails.put(OutputType.dto,
                    joinPath(globalSettings.getOutputDir(), packageSettings.getPackage(Naming.Dto)));
        }
        // 持久层
        RepositoryTemplate repository = strategySettings.repository();
        if (repository.isGenerate()) {
            switch (globalSettings.getPersistType()) {
                // jpa
                case jpa -> outputDetails.put(OutputType.repository,
                        joinPath(globalSettings.getOutputDir(), packageSettings.getPackage(Naming.Repository)));
                // mybatis
                case tk_mybatis, mybatis_plus -> {
                    //
                    outputDetails.put(OutputType.repository,
                            joinPath(globalSettings.getOutputDir(), packageSettings.getPackage(Naming.Mapper)));
                    // mapper xml
                    outputDetails.put(OutputType.xml,
                            joinPath(globalSettings.getXmlOutputDir(), packageSettings.getPackage(Naming.Xml)));
                }
                default -> log.info("没有匹配的持久化类型！");
            }
        }
        //业务层
        ServiceTemplate service = strategySettings.service();
        if (service.isGenerate()) {
            outputDetails.put(OutputType.service,
                    joinPath(globalSettings.getOutputDir(), packageSettings.getPackage(Naming.Service)));
            outputDetails.put(OutputType.serviceImpl,
                    joinPath(globalSettings.getOutputDir(), packageSettings.getPackage(Naming.ServiceImpl)));
        }
        // 控制层
        ControllerTemplate controller = strategySettings.controller();
        if (controller.isGenerate()) {
            outputDetails.put(OutputType.controller,
                    joinPath(globalSettings.getOutputDir(), packageSettings.getPackage(Naming.Controller)));
        }
        outputDetails.put(OutputType.parent, joinPath(globalSettings.getOutputDir(), packageSettings.getPackage(Naming.Parent)));
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
