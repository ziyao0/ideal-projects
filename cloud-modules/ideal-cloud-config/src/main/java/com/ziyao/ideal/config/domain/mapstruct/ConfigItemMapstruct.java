package com.ziyao.ideal.config.domain.mapstruct;

import com.ziyao.ideal.config.domain.entity.ConfigItem;
import com.ziyao.ideal.config.domain.dto.ConfigItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface ConfigItemMapstruct {

    ConfigItemMapstruct INSTANCE = Mappers.getMapper(ConfigItemMapstruct.class);

    /**
     * 转换
     */
    ConfigItem of(ConfigItemDTO configItemDTO);
}
