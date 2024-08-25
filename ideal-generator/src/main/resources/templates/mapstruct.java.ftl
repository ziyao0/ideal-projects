package ${mapstructPkg};

import ${package.Entity}.${entity};
import ${package.DTO}.${table.dtoName};
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
    ${entity} convert(${table.dtoName} ${table.dtoName?uncap_first});
}
