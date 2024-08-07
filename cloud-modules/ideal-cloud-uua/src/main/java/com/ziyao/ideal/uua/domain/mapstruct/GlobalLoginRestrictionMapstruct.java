package com.ziyao.ideal.uua.domain.mapstruct;

import com.ziyao.ideal.uua.domain.entity.GlobalLoginRestriction;
import com.ziyao.ideal.uua.domain.dto.GlobalLoginRestrictionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface GlobalLoginRestrictionMapstruct {

    GlobalLoginRestrictionMapstruct INSTANCE = Mappers.getMapper(GlobalLoginRestrictionMapstruct.class);

    /**
     * 转换
     */
    GlobalLoginRestriction of(GlobalLoginRestrictionDTO globalLoginRestrictionDTO);
}
