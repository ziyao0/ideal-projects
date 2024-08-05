package com.ziyao.ideal.codegen.core;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.ziyao.ideal.codegen.config.CodeGenConfig;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class GlobalConfigBuilder {


    public static void globalConfig(
            GlobalConfig.Builder builder, CodeGenConfig codeGenConfig) {

        builder.outputDir(codeGenConfig.getProjectDir() + "/src/main/java")
                .author(codeGenConfig.getAuthor())
                .dateType(DateType.TIME_PACK)
                .disableOpenDir();
    }
}
