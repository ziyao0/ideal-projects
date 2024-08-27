package com.ziyao.ideal.generator.query;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.config.po.TableInfo;

import java.util.List;


public interface IDatabaseQuery {

    /**
     * 获取表信息
     *
     * @return 表信息
     */
    @NonNull
    List<TableInfo> queryTables();

}
