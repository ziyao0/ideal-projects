package com.ziyao.ideal.uaa.domain.convertor;

import com.ziyao.ideal.uaa.domain.entity.LoginConfig;
import com.ziyao.ideal.uaa.domain.dto.LoginConfigDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface LoginConfigConvertor {

    LoginConfigConvertor INSTANCE = Mappers.getMapper(LoginConfigConvertor.class);

    /**
     * 转换
     */
    LoginConfig convert(LoginConfigDTO loginConfigDTO);
}
