package com.ziyao.ideal.generator.core;

import lombok.Getter;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public enum Templates {
    /**
     * 实体模板路径
     */
    entity("/templates/entity.java"),
    /**
     * 数据传输对象模板路径
     */
    entity_dto("/templates/entityDTO.java"),
    /**
     * JPA模板路径
     */
    repository("/templates/repository.java"),
    /**
     * Mapper模板路径
     */
    mapper_java("/templates/mapper.java"),
    /**
     * MapperXml模板路径
     */
    mapper_xml("/templates/mapper.xml"),
    /**
     * Service模板路径
     */
    service("/templates/service.java"),
    /**
     * ServiceImpl模板路径
     */
    service_impl("/templates/serviceImpl.java"),
    /**
     * 控制器模板路径
     */
    controller("/templates/controller.java"),
    ;
    /**
     * 模板内容
     */
    private final String template;

    Templates(String template) {
        this.template = template;
    }
}
