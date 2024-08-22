package com.ziyao.ideal.uua.domain.convertor;

import com.ziyao.ideal.uua.domain.dto.GlobalLoginRestrictionDTO;
import com.ziyao.ideal.uua.domain.entity.GlobalLoginRestriction;
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
