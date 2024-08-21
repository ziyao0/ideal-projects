package ${mapstructPkg};

import ${package.Entity}.${entity};
import ${dto}.${entity}DTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ${author}
 */
@Mapper
public interface ${entity}Convertor {

    ${entity}Convertor INSTANCE = Mappers.getMapper(${entity}Convertor.class);

    /**
     * 转换
     */
    ${entity} convert(${entity}DTO ${entity?uncap_first}DTO);
}
