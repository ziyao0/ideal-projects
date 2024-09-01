package com.ziyao.ideal.generator.core;

import lombok.Getter;

/**
 * 持久层类型
 *
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public enum PersistType {

    /**
     * 使用spring提供的spring-data-jpa来生成持久层
     */
    JPA("jpa"),
    /**
     * mybatis-plus
     */
    MYBATIS_PLUS("mybatisPlus"),
    /**
     * tk-mybatis
     */
    TK_MYBATIS("tkMybatis"),
    ;

    private final String type;

    PersistType(String type) {
        this.type = type;
    }
}
