package com.ziyao.ideal.generator.core.meta;

import com.ziyao.ideal.generator.core.NamingStrategy;
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

    private final String capitalName;
    /**
     * 字段类型
     */
    private final String propertyType;
    /**
     * 是否为主键
     */
    @Setter
    private boolean primary;
    /**
     * 字段注释
     */
    @Setter
    private String comment;


    public Field(String name, String propertyName, String propertyType) {
        this.name = name;
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.capitalName = NamingStrategy.capitalFirst(propertyName);
    }

    public static Field of(String name, String propertyName, String propertyType) {
        return new Field(name, propertyName, propertyType);
    }
}