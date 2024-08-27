package com.ziyao.ideal.generator.type;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.config.GlobalConfig;
import com.ziyao.ideal.generator.config.po.TableField;
import com.ziyao.ideal.generator.config.rules.IColumnType;

/**
 * 类型转换处理器
 */
public interface ITypeConvertHandler {

    /**
     * 转换字段类型
     *
     * @param globalConfig 全局配置
     * @param typeRegistry 类型注册信息
     * @param metaInfo     字段元数据信息
     * @return 子类类型
     */
    @NonNull
    IColumnType convert(GlobalConfig globalConfig, TypeRegistry typeRegistry, TableField.MetaInfo metaInfo);

}
