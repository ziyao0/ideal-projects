package com.ziyao.ideal.uua.domain.mapstruct;

import com.ziyao.ideal.uua.domain.entity.UserLoginRestriction;
import com.ziyao.ideal.uua.domain.dto.UserLoginRestrictionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface UserLoginRestrictionMapstruct {

    UserLoginRestrictionMapstruct INSTANCE = Mappers.getMapper(UserLoginRestrictionMapstruct.class);

    /**
     * 转换
     */
    UserLoginRestriction of(UserLoginRestrictionDTO userLoginRestrictionDTO);
}
