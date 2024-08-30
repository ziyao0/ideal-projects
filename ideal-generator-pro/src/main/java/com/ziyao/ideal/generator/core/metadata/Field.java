package com.ziyao.ideal.generator.core.metadata;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class Field {
    /**
     * 字段名称
     */
    private final String name;

    /**
     * 属性名称
     */
    private final String propertyName;

    /**
     * 字段类型
     */
    private final String propertyType;

    /**
     * 字段注释
     */
    @Setter
    private String comment;
    /**
     * 数据库字段（关键字含转义符号）
     */
    @Getter
    private String columnName;


    public Field(String name, String propertyName, String propertyType) {
        this.name = name;
        this.propertyName = propertyName;
        this.propertyType = propertyType;
    }

    public static Field of(String name, String propertyName, String propertyType) {
        return new Field(name, propertyName, propertyType);
    }
}