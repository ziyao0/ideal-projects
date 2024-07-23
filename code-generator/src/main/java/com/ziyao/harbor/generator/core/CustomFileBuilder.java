package com.ziyao.harbor.generator.core;

import com.baomidou.mybatisplus.generator.config.builder.CustomFile;

/**
 * @author zhangziyao
 */
public abstract class CustomFileBuilder {


    public static CustomFile createDto(String fileName, String templatePath) {
        return new CustomFile.Builder().fileName(fileName)
                .templatePath(templatePath)
                .build();

    }
}
