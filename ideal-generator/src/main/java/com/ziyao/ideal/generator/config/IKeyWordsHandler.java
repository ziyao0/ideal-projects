package com.ziyao.ideal.generator.config;

import com.ziyao.ideal.core.lang.NonNull;

import java.util.Collection;

/**
 * 关键字处理接口
 * <p>
 */
public interface IKeyWordsHandler {

    /**
     * 获取关键字
     *
     * @return 关键字集合
     */
    @NonNull
    Collection<String> getKeyWords();

    /**
     * 格式化关键字格式
     *
     * @return 格式
     */
    @NonNull
    String formatStyle();

    /**
     * 是否为关键字
     *
     * @param columnName 字段名称
     * @return 是否为关键字
     */
    boolean isKeyWords(@NonNull String columnName);

    /**
     * 格式化字段
     *
     * @param columnName 字段名称
     * @return 格式化字段
     */
    @NonNull
    default String formatColumn(@NonNull String columnName) {
        return String.format(formatStyle(), columnName);
    }

}
