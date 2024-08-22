package com.ziyao.ideal.uaa.domain.convertor;

import com.ziyao.ideal.uaa.domain.entity.GlobalLoginRestriction;
import com.ziyao.ideal.uaa.domain.dto.GlobalLoginRestrictionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface GlobalLoginRestrictionConvertor {

    GlobalLoginRestrictionConvertor INSTANCE = Mappers.getMapper(GlobalLoginRestrictionConvertor.class);

    /**
     * 转换
     */
    GlobalLoginRestriction convert(GlobalLoginRestrictionDTO globalLoginRestrictionDTO);
}
