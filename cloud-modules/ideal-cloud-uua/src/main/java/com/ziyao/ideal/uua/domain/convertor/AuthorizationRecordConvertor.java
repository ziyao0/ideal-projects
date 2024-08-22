package com.ziyao.ideal.uua.domain.convertor;

import com.ziyao.ideal.uua.domain.dto.AuthorizationRecordDTO;
import com.ziyao.ideal.uua.domain.entity.AuthorizationRecord;
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
