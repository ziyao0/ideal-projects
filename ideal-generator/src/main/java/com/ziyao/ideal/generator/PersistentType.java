package com.ziyao.ideal.generator;

import lombok.Getter;

/**
 * 持久层类型
 *
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public enum PersistentType {

    /**
     * 使用spring提供的spring-data-jpa来生成持久层
     */
    JPA("jpa"),
    /**
     * mybatis-plus
     */
    MYBATIS_PLUS("mysqlPlus"),
    /**
     * tk-mybatis
     */
    TK_MYBATIS("tkMybatis"),
    ;

    private final String type;

    PersistentType(String type) {
        this.type = type;
    }
}
