package com.ziyao.ideal.uua.domain.convertor;

import com.ziyao.ideal.uua.domain.dto.UserRoleDTO;
import com.ziyao.ideal.uua.domain.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface UserRoleConvertor {

    UserRoleConvertor INSTANCE = Mappers.getMapper(UserRoleConvertor.class);

    /**
     * 转换
     */
    UserRole convert(UserRoleDTO userRoleDTO);
}
