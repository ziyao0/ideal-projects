package com.ziyao.ideal.uua.domain.convertor;

import com.ziyao.ideal.uua.domain.dto.ApplicationDTO;
import com.ziyao.ideal.uua.domain.entity.Application;
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
