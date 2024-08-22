package com.ziyao.ideal.uua.domain.convertor;

import com.ziyao.ideal.uua.domain.dto.LoginRecordDTO;
import com.ziyao.ideal.uua.domain.entity.LoginRecord;
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
