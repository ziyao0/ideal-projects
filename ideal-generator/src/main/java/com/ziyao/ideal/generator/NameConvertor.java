package com.ziyao.ideal.generator;


import com.ziyao.ideal.core.lang.NonNull;

/**
 * 转换输出文件名称
 */
@FunctionalInterface
public interface NameConvertor {

    @NonNull
    String convert(String entityName);
}
