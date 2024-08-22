package com.ziyao.ideal.uaa.domain.convertor;

import com.ziyao.ideal.uaa.domain.entity.AuthorizationRecord;
import com.ziyao.ideal.uaa.domain.dto.AuthorizationRecordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface AuthorizationRecordConvertor {

    AuthorizationRecordConvertor INSTANCE = Mappers.getMapper(AuthorizationRecordConvertor.class);

    /**
     * 转换
     */
    AuthorizationRecord convert(AuthorizationRecordDTO authorizationRecordDTO);
}
