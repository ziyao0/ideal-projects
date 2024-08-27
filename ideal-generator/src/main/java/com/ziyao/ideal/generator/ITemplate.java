package com.ziyao.ideal.generator;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.config.po.TableInfo;

import java.io.Serializable;
import java.util.Map;

/**
 * 渲染模板接口
 */
public interface ITemplate extends Serializable {

    @NonNull
    Map<String, Object> renderData(@NonNull TableInfo tableInfo);

}
