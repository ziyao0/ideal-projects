package com.ziyao.ideal.uua.domain.mapstruct;

import com.ziyao.ideal.uua.domain.entity.LoginConfig;
import com.ziyao.ideal.uua.domain.dto.LoginConfigDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface LoginConfigMapstruct {

    LoginConfigMapstruct INSTANCE = Mappers.getMapper(LoginConfigMapstruct.class);

    /**
     * 转换
     */
    LoginConfig of(LoginConfigDTO loginConfigDTO);
}
