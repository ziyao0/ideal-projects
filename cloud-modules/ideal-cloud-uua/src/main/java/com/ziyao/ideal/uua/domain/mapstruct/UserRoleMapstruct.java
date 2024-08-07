package com.ziyao.ideal.uua.domain.mapstruct;

import com.ziyao.ideal.uua.domain.entity.UserRole;
import com.ziyao.ideal.uua.domain.dto.UserRoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface UserRoleMapstruct {

    UserRoleMapstruct INSTANCE = Mappers.getMapper(UserRoleMapstruct.class);

    /**
     * 转换
     */
    UserRole of(UserRoleDTO userRoleDTO);
}
