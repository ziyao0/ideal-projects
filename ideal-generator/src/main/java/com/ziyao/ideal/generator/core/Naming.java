package com.ziyao.ideal.generator.core;

import lombok.Getter;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public enum Naming {

    Dto((entityName -> entityName + "DTO")),
    Entity((entityName -> entityName)),
    Mapper((entityName -> entityName + "Mapper")),
    Xml((entityName -> entityName + "Mapper")),
    Repository((entityName -> entityName + "RepositoryJpa")),
    Service((entityName -> entityName + "Service")),
    ServiceImpl((entityName -> entityName + "ServiceImpl")),
    Controller((entityName -> entityName + "Controller")),
    ModuleName(null),
    Parent(null),
    ;

    private final OutputNameConvertor converter;

    Naming(OutputNameConvertor converter) {
        this.converter = converter;
    }
}
