package com.ziyao.ideal.generator.core;


import com.ziyao.ideal.core.lang.NonNull;

/**
 * 转换输出文件名称
 */
@FunctionalInterface
public interface OutputNameConvertor {

    @NonNull
    String convert(String entityName);
}
