package com.ziyao.ideal.uua.domain.convertor;

import com.ziyao.ideal.uua.domain.entity.Role;
import com.ziyao.ideal.uua.domain.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface RoleConvertor {

    RoleConvertor INSTANCE = Mappers.getMapper(RoleConvertor.class);

    /**
     * 转换
     */
    Role convert(RoleDTO roleDTO);
}
