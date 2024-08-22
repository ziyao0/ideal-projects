package com.ziyao.ideal.uaa.domain.convertor;

import com.ziyao.ideal.uaa.domain.entity.LoginRecord;
import com.ziyao.ideal.uaa.domain.dto.LoginRecordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface LoginRecordConvertor {

    LoginRecordConvertor INSTANCE = Mappers.getMapper(LoginRecordConvertor.class);

    /**
     * 转换
     */
    LoginRecord convert(LoginRecordDTO loginRecordDTO);
}
