package com.ziyao.ideal.uua.domain.mapstruct;

import com.ziyao.ideal.uua.domain.entity.Role;
import com.ziyao.ideal.uua.domain.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface RoleMapstruct {

    RoleMapstruct INSTANCE = Mappers.getMapper(RoleMapstruct.class);

    /**
     * 转换
     */
    Role of(RoleDTO roleDTO);
}
