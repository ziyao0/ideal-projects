package com.ziyao.ideal.codegen.core;

import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.ziyao.ideal.codegen.config.CodeGenConfig;

import java.util.Collections;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class PackageConfigBuilder {


    public static void packageConfig(
            PackageConfig.Builder builder, CodeGenConfig codeGenConfig) {
        builder.moduleName(codeGenConfig.getModuleName())// 设置父包模块名
                .parent(codeGenConfig.getParent()) // 设置父包名
                .mapper("repository.mapper")
                .entity("domain.entity")
                .controller("controllers")
                .pathInfo(Collections.singletonMap(
                        OutputFile.xml, codeGenConfig.getProjectDir() + "/src/main/resources/mapper"));// 设置mapperXml生成路径
    }
}