package com.ziyao.ideal.uua.domain.convertor;

import com.ziyao.ideal.uua.domain.dto.MenuDTO;
import com.ziyao.ideal.uua.domain.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface MenuConvertor {

    MenuConvertor INSTANCE = Mappers.getMapper(MenuConvertor.class);

    /**
     * 转换
     */
    Menu convert(MenuDTO menuDTO);
}
