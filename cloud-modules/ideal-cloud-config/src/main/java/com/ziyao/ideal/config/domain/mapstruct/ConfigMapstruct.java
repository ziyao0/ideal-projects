package com.ziyao.ideal.config.domain.mapstruct;

import com.ziyao.ideal.config.domain.dto.ConfigDTO;
import com.ziyao.ideal.config.domain.entity.Config;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface ConfigMapstruct {

    ConfigMapstruct INSTANCE = Mappers.getMapper(ConfigMapstruct.class);

    /**
     * 转换
     */
    Config of(ConfigDTO configDTO);
}
