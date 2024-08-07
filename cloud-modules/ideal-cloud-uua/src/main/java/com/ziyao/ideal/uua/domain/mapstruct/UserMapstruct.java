package com.ziyao.ideal.uua.domain.mapstruct;

import com.ziyao.ideal.uua.domain.entity.User;
import com.ziyao.ideal.uua.domain.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface UserMapstruct {

    UserMapstruct INSTANCE = Mappers.getMapper(UserMapstruct.class);

    /**
     * 转换
     */
    User of(UserDTO userDTO);
}
