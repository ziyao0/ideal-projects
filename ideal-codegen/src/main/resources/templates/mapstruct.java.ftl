package ${mapstructPkg};

import ${package.Entity}.${entity};
import ${dto}.${entity}DTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ${author}
 */
@Mapper
public interface ${entity}Mapstruct {

    ${entity}Mapstruct INSTANCE = Mappers.getMapper(${entity}Mapstruct.class);

    /**
     * 转换
     */
    ${entity} of(${entity}DTO ${entity?uncap_first}DTO);
}
