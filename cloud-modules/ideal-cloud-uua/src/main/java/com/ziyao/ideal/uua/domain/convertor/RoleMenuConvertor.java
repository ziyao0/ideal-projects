package com.ziyao.ideal.uua.domain.convertor;

import com.ziyao.ideal.uua.domain.entity.RoleMenu;
import com.ziyao.ideal.uua.domain.dto.RoleMenuDTO;
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
