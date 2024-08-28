package com.ziyao.ideal.generator;

import lombok.Getter;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public enum NameEnum {

    Dto((entityName -> entityName + "DTO")),
    Entity((entityName -> entityName)),
    Mapper((entityName -> entityName + "Mapper")),
    Repository((entityName -> entityName + "RepositoryJpa")),
    Service((entityName -> entityName + "Service")),
    ServiceImpl((entityName -> entityName + "ServiceImpl")),
    Controller((entityName -> entityName + "Controller")),
    ;


    private final NameConvertor converter;

    NameEnum(NameConvertor converter) {
        this.converter = converter;
    }
}
