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
package com.ziyao.ideal.generator.config.builder;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.text.StrPool;
import com.ziyao.ideal.generator.config.*;
import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 路径信息处理
 * <p>
 * hubin
 *
 * @since 2020-10-06
 */
class PathInfoHandler {

    /**
     * 输出文件Map
     */
    @Getter
    private final Map<OutputFile, String> pathInfo = new HashMap<>();

    /**
     * 输出目录
     */
    private final String outputDir;

    /**
     * 包配置信息
     */
    private final PackageConfig packageConfig;

    PathInfoHandler(GlobalConfig globalConfig, StrategyConfig strategyConfig, PackageConfig packageConfig) {
        this.outputDir = globalConfig.getOutputDir();
        this.packageConfig = packageConfig;
        // 设置默认输出路径
        this.setDefaultPathInfo(globalConfig, strategyConfig);
        // 覆盖自定义路径
        Map<OutputFile, String> pathInfo = packageConfig.getPathInfo();
        if (!Collections.isEmpty(pathInfo)) {
            this.pathInfo.putAll(pathInfo);
        }
    }

    /**
     * 设置默认输出路径
     *
     * @param globalConfig   全局配置
     * @param strategyConfig 模板配置
     */
    private void setDefaultPathInfo(GlobalConfig globalConfig, StrategyConfig strategyConfig) {
        boolean isJpa = globalConfig.isJpa();
        Entity entity = strategyConfig.entity();
        if (entity.isGenerateEntity()) {
            putPathInfo(isJpa ? entity.getEntityJpaTemplate() : entity.getEntityTemplate(), OutputFile.entity, ConstVal.ENTITY);
        }
        if (entity.isGenerateDTO()) {
            putPathInfo(entity.getDtoTemplate(), OutputFile.dto, ConstVal.DTO);
        }
        if (isJpa) {
            Repository repository = strategyConfig.repository();
            if (repository.isGenerateRepository()) {
                putPathInfo(repository.getRepositoryTemplate(), OutputFile.repository, ConstVal.REPOSITORY);
            }
        } else {
            Mapper mapper = strategyConfig.mapper();
            if (mapper.isGenerateMapper()) {
                putPathInfo(mapper.getMapperTemplatePath(), OutputFile.mapper, ConstVal.MAPPER);
            }
            if (mapper.isGenerateMapperXml()) {
                putPathInfo(mapper.getMapperXmlTemplatePath(), OutputFile.xml, ConstVal.XML);
            }
        }


        Service service = strategyConfig.service();
        if (service.isGenerateService()) {
            putPathInfo(isJpa ? service.getServiceJpaTemplate() : service.getServiceTemplate(), OutputFile.service, ConstVal.SERVICE);
        }
        if (service.isGenerateServiceImpl()) {
            putPathInfo(isJpa ? service.getServiceImplJpaTemplate() : service.getServiceImplTemplate(), OutputFile.serviceImpl, ConstVal.SERVICE_IMPL);
        }
        Controller controller = strategyConfig.controller();
        if (controller.isGenerate()) {
            putPathInfo(controller.getTemplatePath(), OutputFile.controller, ConstVal.CONTROLLER);
        }
        putPathInfo(OutputFile.parent, ConstVal.PARENT);
    }

    private void putPathInfo(String template, OutputFile outputFile, String module) {
        if (Strings.hasLength(template)) {
            putPathInfo(outputFile, module);
        }
    }

    private void putPathInfo(OutputFile outputFile, String module) {
        pathInfo.putIfAbsent(outputFile, joinPath(outputDir, packageConfig.getPackageInfo(module)));
    }

    /**
     * 连接路径字符串
     *
     * @param parentDir   路径常量字符串
     * @param packageName 包名
     * @return 连接后的路径
     */
    private String joinPath(String parentDir, String packageName) {
        if (Strings.isEmpty(parentDir)) {
            parentDir = System.getProperty(ConstVal.JAVA_TMPDIR);
        }
        if (!Strings.endsWith(parentDir, File.separator)) {
            parentDir += File.separator;
        }
        packageName = packageName.replaceAll("\\.", StrPool.BACKSLASH + File.separator);
        return parentDir + packageName;
    }
}
