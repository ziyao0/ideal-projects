package com.ziyao.ideal.uua.domain.mapstruct;

import com.ziyao.ideal.uua.domain.entity.Application;
import com.ziyao.ideal.uua.domain.dto.ApplicationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface ApplicationMapstruct {

    ApplicationMapstruct INSTANCE = Mappers.getMapper(ApplicationMapstruct.class);

    /**
     * 转换
     */
    Application of(ApplicationDTO applicationDTO);
}
