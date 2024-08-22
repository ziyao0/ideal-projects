package com.ziyao.ideal.uaa.domain.convertor;

import com.ziyao.ideal.uaa.domain.entity.Authorization;
import com.ziyao.ideal.uaa.domain.dto.AuthorizationDTO;
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
