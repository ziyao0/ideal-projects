package com.ziyao.ideal.uaa.domain.convertor;

import com.ziyao.ideal.uaa.domain.entity.Application;
import com.ziyao.ideal.uaa.domain.dto.ApplicationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface ApplicationConvertor {

    ApplicationConvertor INSTANCE = Mappers.getMapper(ApplicationConvertor.class);

    /**
     * 转换
     */
    Application convert(ApplicationDTO applicationDTO);
}
