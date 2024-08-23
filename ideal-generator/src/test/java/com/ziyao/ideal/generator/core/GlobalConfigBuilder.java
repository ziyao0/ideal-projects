package com.ziyao.ideal.generator.core;


import com.ziyao.ideal.generator.config.CodeGenConfig;
import com.ziyao.ideal.generator.config.GlobalConfig;
import com.ziyao.ideal.generator.config.rules.DateType;

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
