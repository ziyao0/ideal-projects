package com.ziyao.ideal.generator.config;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.config.po.TableField;
import com.ziyao.ideal.generator.config.rules.IColumnType;

/**
 * 数据库字段类型转换
 */
public interface ITypeConvert {

    /**
     * 执行类型转换
     *
     * @param globalConfig 全局配置
     * @param tableField   字段列信息
     * @return ignore
     */
    default IColumnType processTypeConvert(@NonNull GlobalConfig globalConfig, @NonNull TableField tableField) {
        return processTypeConvert(globalConfig, tableField.getColumnType().getType());
    }

    /**
     * 执行类型转换
     *
     * @param globalConfig 全局配置
     * @param fieldType    字段类型
     * @return ignore
     */
    IColumnType processTypeConvert(@NonNull GlobalConfig globalConfig, @NonNull String fieldType);

}
