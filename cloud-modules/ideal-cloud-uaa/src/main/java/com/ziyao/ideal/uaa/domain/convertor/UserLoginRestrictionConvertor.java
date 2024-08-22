package com.ziyao.ideal.uaa.domain.convertor;

import com.ziyao.ideal.uaa.domain.entity.UserLoginRestriction;
import com.ziyao.ideal.uaa.domain.dto.UserLoginRestrictionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface UserLoginRestrictionConvertor {

    UserLoginRestrictionConvertor INSTANCE = Mappers.getMapper(UserLoginRestrictionConvertor.class);

    /**
     * 转换
     */
    UserLoginRestriction convert(UserLoginRestrictionDTO userLoginRestrictionDTO);
}
