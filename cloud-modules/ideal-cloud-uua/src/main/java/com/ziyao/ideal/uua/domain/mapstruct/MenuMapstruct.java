package com.ziyao.ideal.uua.domain.mapstruct;

import com.ziyao.ideal.uua.domain.entity.Menu;
import com.ziyao.ideal.uua.domain.dto.MenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface MenuMapstruct {

    MenuMapstruct INSTANCE = Mappers.getMapper(MenuMapstruct.class);

    /**
     * 转换
     */
    Menu of(MenuDTO menuDTO);
}
