package com.ziyao.ideal.uua.domain.mapstruct;

import com.ziyao.ideal.uua.domain.entity.LoginRecord;
import com.ziyao.ideal.uua.domain.dto.LoginRecordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface LoginRecordMapstruct {

    LoginRecordMapstruct INSTANCE = Mappers.getMapper(LoginRecordMapstruct.class);

    /**
     * 转换
     */
    LoginRecord of(LoginRecordDTO loginRecordDTO);
}
