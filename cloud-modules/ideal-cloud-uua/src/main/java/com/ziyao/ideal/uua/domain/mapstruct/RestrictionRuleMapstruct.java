package com.ziyao.ideal.uua.domain.mapstruct;

import com.ziyao.ideal.uua.domain.entity.RestrictionRule;
import com.ziyao.ideal.uua.domain.dto.RestrictionRuleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface RestrictionRuleMapstruct {

    RestrictionRuleMapstruct INSTANCE = Mappers.getMapper(RestrictionRuleMapstruct.class);

    /**
     * 转换
     */
    RestrictionRule of(RestrictionRuleDTO restrictionRuleDTO);
}
