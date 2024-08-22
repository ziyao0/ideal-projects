package com.ziyao.ideal.uua.domain.convertor;

import com.ziyao.ideal.uua.domain.dto.UserLoginRestrictionDTO;
import com.ziyao.ideal.uua.domain.entity.UserLoginRestriction;
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
