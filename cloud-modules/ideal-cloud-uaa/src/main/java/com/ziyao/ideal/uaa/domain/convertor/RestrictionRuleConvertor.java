package com.ziyao.ideal.uaa.domain.convertor;

import com.ziyao.ideal.uaa.domain.entity.RestrictionRule;
import com.ziyao.ideal.uaa.domain.dto.RestrictionRuleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface RestrictionRuleConvertor {

    RestrictionRuleConvertor INSTANCE = Mappers.getMapper(RestrictionRuleConvertor.class);

    /**
     * 转换
     */
    RestrictionRule convert(RestrictionRuleDTO restrictionRuleDTO);
}
