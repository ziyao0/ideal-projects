package com.ziyao.ideal.uaa.domain.convertor;

import com.ziyao.ideal.uaa.domain.entity.RoleMenu;
import com.ziyao.ideal.uaa.domain.dto.RoleMenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface RoleMenuConvertor {

    RoleMenuConvertor INSTANCE = Mappers.getMapper(RoleMenuConvertor.class);

    /**
     * 转换
     */
    RoleMenu convert(RoleMenuDTO roleMenuDTO);
}
