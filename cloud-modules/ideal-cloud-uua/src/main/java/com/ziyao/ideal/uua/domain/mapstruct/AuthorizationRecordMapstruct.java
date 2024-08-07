package com.ziyao.ideal.uua.domain.mapstruct;

import com.ziyao.ideal.uua.domain.entity.AuthorizationRecord;
import com.ziyao.ideal.uua.domain.dto.AuthorizationRecordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface AuthorizationRecordMapstruct {

    AuthorizationRecordMapstruct INSTANCE = Mappers.getMapper(AuthorizationRecordMapstruct.class);

    /**
     * 转换
     */
    AuthorizationRecord of(AuthorizationRecordDTO authorizationRecordDTO);
}
