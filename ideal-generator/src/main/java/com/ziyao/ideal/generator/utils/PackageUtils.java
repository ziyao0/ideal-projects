package com.ziyao.ideal.generator.utils;

/**
 * @author ziyao zhang
 */
public abstract class PackageUtils {

    public static final String ENTITY_PKG = "javax.persistence.Entity";
    public static final String ID_PKG = "javax.persistence.Id";
    public static final String TABLE_PKG = "javax.persistence.Table";
    public static final String COLUMN_PKG = "javax.persistence.Column";
    /**
     * mybatis plus
     */
    public static final String MYBATIS_PLUS_TABLE_NAME_PKG = "com.baomidou.mybatisplus.annotation.TableName";
    public static final String MYBATIS_PLUS_TABLE_ID_PKG = "com.baomidou.mybatisplus.annotation.TableId";
    public static final String MYBATIS_PLUS_TABLE_FIELD_PKG = "com.baomidou.mybatisplus.annotation.TableField";
    public static final String MYBATIS_PLUS_LAMBDA_QUERY_WRAPPER_PKG = "com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper";
    public static final String MYBATIS_PLUS_WRAPPERS_PKG = "com.baomidou.mybatisplus.core.toolkit.Wrappers";
}
