package com.ziyao.ideal.uua.domain.convertor;

import com.ziyao.ideal.uua.domain.dto.AuthorizationDTO;
import com.ziyao.ideal.uua.domain.entity.Authorization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface AuthorizationConvertor {

    AuthorizationConvertor INSTANCE = Mappers.getMapper(AuthorizationConvertor.class);

    /**
     * 转换
     */
    Authorization convert(AuthorizationDTO authorizationDTO);
}
