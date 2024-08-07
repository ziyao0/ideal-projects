package com.ziyao.ideal.uua.domain.mapstruct;

import com.ziyao.ideal.uua.domain.entity.Authorization;
import com.ziyao.ideal.uua.domain.dto.AuthorizationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface AuthorizationMapstruct {

    AuthorizationMapstruct INSTANCE = Mappers.getMapper(AuthorizationMapstruct.class);

    /**
     * 转换
     */
    Authorization of(AuthorizationDTO authorizationDTO);
}
