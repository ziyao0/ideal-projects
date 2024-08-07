package com.ziyao.ideal.uua.domain.mapstruct;

import com.ziyao.ideal.uua.domain.dto.UserDTO;
import com.ziyao.ideal.uua.domain.entity.RoleMenu;
import com.ziyao.ideal.uua.domain.dto.RoleMenuDTO;
import com.ziyao.ideal.uua.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface RoleMenuMapstruct {

    RoleMenuMapstruct INSTANCE = Mappers.getMapper(RoleMenuMapstruct.class);

    /**
     * 转换
     */
    RoleMenu of(RoleMenuDTO roleMenuDTO);

    User of(UserDTO userDTO);
}
