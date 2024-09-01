package com.ziyao.ideal.generator.core;

import java.io.File;

/**
 * 输出文件接口
 */
public interface OutputFileCreator {

    /**
     * 创建文件
     *
     * @param filePath   默认文件路径
     * @param outputType 输出文件类型
     * @return {@link File}
     */
    File create(String filePath, OutputType outputType);
}
