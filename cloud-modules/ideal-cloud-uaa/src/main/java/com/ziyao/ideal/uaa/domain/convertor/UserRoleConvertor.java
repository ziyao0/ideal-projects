package com.ziyao.ideal.uaa.domain.convertor;

import com.ziyao.ideal.uaa.domain.entity.UserRole;
import com.ziyao.ideal.uaa.domain.dto.UserRoleDTO;
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
