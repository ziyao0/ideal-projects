package com.ziyao.ideal.generator.function;


import com.ziyao.ideal.core.lang.NonNull;

/**
 * 转换输出文件名称
 */
@FunctionalInterface
public interface ConverterFileName {

    @NonNull
    String convert(String entityName);
}
