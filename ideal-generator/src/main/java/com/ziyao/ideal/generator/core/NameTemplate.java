package com.ziyao.ideal.generator.core;

import com.ziyao.ideal.generator.function.ConverterFileName;
import lombok.Getter;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public enum NameTemplate {

    Dto((entityName -> entityName + "DTO")),
    Entity((entityName -> entityName + "Entity")),
    Mapper((entityName -> entityName + "Mapper")),
    Repository((entityName -> entityName + "Repository")),
    Service((entityName -> entityName + "Service")),
    ServiceImpl((entityName -> entityName + "ServiceImpl")),
    Controller((entityName -> entityName + "Controller")),
    ;


    private final ConverterFileName converter;

    NameTemplate(ConverterFileName converter) {
        this.converter = converter;
    }
}
