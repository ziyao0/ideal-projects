/*
 * Copyright (c) 2011-2024, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ziyao.ideal.generator.config;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 表数据查询接口
 *
 */
public interface IDbQuery {
    /**
     * 表信息查询 SQL
     */
    String tablesSql();

    /**
     * 表字段信息查询 SQL
     */
    String tableFieldsSql();

    /**
     * 表名称
     */
    String tableName();

    /**
     * 表注释
     */
    String tableComment();

    /**
     * 字段名称
     */
    String fieldName();

    /**
     * 字段类型
     */
    String fieldType();

    /**
     * 字段注释
     */
    String fieldComment();

    /**
     * 主键字段
     */
    String fieldKey();

    /**
     * 判断主键是否为identity
     *
     * @param results ResultSet
     * @return 主键是否为identity
     * @throws SQLException ignore
     */
    boolean isKeyIdentity(ResultSet results) throws SQLException;

    /**
     * 自定义字段名称
     */
    String[] fieldCustom();

    /**
     * 获取主键sql
     *
     * @param dataSourceConfig 数据库配置信息
     * @param tableName        表名
     * @return 主键查询sql
     * @since 3.5.7
     */
    default String primaryKeySql(DataSourceConfig dataSourceConfig, String tableName) {
        return null;
    }

}
