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
package com.ziyao.ideal.generator.core.metadata;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.generator.config.po.TableInfo;
import com.ziyao.ideal.generator.mybatisplus.AnnotationHandler;
import com.ziyao.ideal.generator.mybatisplus.TableField;
import com.ziyao.ideal.generator.util.ClassUtils;
import com.ziyao.ideal.generator.util.ReflectionUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.type.SimpleTypeRegistry;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 实体类反射表辅助类
 * </p>
 *
 * @author hubin sjy
 * @since 2016-09-09
 */
public class TableInfoHelper {

    private static final Log logger = LogFactory.getLog(TableInfoHelper.class);

    /**
     * 储存反射类表信息
     */
    private static final Map<Class<?>, TableInfo> TABLE_INFO_CACHE = new ConcurrentHashMap<>();

    /**
     * 储存表名对应的反射类表信息
     */
    private static final Map<String, TableInfo> TABLE_NAME_INFO_CACHE = new ConcurrentHashMap<>();

    /**
     * 默认表主键名称
     */
    private static final String DEFAULT_ID_NAME = "id";

    /**
     * <p>
     * 获取实体映射表信息
     * </p>
     *
     * @param clazz 反射实体类
     * @return 数据库表反射信息
     */
    public static TableInfo getTableInfo(Class<?> clazz) {
        if (clazz == null || clazz.isPrimitive() || SimpleTypeRegistry.isSimpleType(clazz) || clazz.isInterface()) {
            return null;
        }
        // https://github.com/baomidou/mybatis-plus/issues/299
        Class<?> targetClass = ClassUtils.getUserClass(clazz);
        TableInfo tableInfo = TABLE_INFO_CACHE.get(targetClass);
        if (null != tableInfo) {
            return tableInfo;
        }
        //尝试获取父类缓存
        Class<?> currentClass = clazz;
        while (null == tableInfo && Object.class != currentClass) {
            currentClass = currentClass.getSuperclass();
            tableInfo = TABLE_INFO_CACHE.get(ClassUtils.getUserClass(currentClass));
        }
        //把父类的移到子类中来
        if (tableInfo != null) {
            TABLE_INFO_CACHE.put(targetClass, tableInfo);
        }
        return tableInfo;
    }

    /**
     * <p>
     * 根据表名获取实体映射表信息
     * </p>
     *
     * @param tableName 表名
     * @return 数据库表反射信息
     */
    public static TableInfo getTableInfo(String tableName) {
        if (Strings.isEmpty(tableName)) {
            return null;
        }
        return TABLE_NAME_INFO_CACHE.get(tableName);
    }

    /**
     * <p>
     * 获取所有实体映射表信息
     * </p>
     *
     * @return 数据库表反射信息集合
     */
    public static List<TableInfo> getTableInfos() {
        return List.copyOf(TABLE_INFO_CACHE.values());
    }

    /**
     * 清空实体表映射缓存信息
     *
     * @param entityClass 实体 Class
     */
    public static void remove(Class<?> entityClass) {
        TABLE_INFO_CACHE.remove(entityClass);
    }


    /**
     * <p>
     * 获取该类的所有属性列表
     * </p>
     *
     * @param clazz             反射类
     * @param annotationHandler 注解处理类
     * @return 属性集合
     */
    public static List<Field> getAllFields(Class<?> clazz, AnnotationHandler annotationHandler) {
        List<Field> fieldList = ReflectionUtils.getFieldList(ClassUtils.getUserClass(clazz));
        return fieldList.stream()
                .filter(field -> {
                    /* 过滤注解非表字段属性 */
                    TableField tableField = annotationHandler.getAnnotation(field, TableField.class);
                    return (tableField == null || tableField.exist());
                }).collect(toList());
    }

}
