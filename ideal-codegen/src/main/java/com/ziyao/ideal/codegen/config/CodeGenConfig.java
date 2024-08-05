package com.ziyao.ideal.codegen.config;

import com.baomidou.mybatisplus.generator.config.rules.DateType;
import lombok.Data;

/**
 * @author ziyao zhang
 */
@Data
public class CodeGenConfig {

    private boolean enableJpa;

    /**
     * 生成文件的输出目录【默认 D 盘根目录】
     */
    private String projectDir;
    /**
     * 是否覆盖已有文件
     */
    private boolean fileOverride = false;
    /**
     * 开发人员
     */
    private String author = "ziyao";

    private String userName;
    private String url;
    private String password;

    //PackageConfig

    private String moduleName;
    private String parent;


    // StrategyConfig
    private String superControllerClass;
    /**
     * 表名，多个英文逗号分割
     */
    private String include;

}
