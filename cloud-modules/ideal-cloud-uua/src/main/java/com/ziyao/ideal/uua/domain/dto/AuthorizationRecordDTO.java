package com.ziyao.ideal.uua.domain.dto;

import com.ziyao.ideal.uua.domain.convertor.AuthorizationRecordConvertor;
import com.ziyao.ideal.uua.domain.entity.AuthorizationRecord;
import com.ziyao.ideal.web.orm.EntityDTO;
import lombok.Data;


import java.io.Serializable;

/**
 * <p>
 * 授权记录表
 * </p>
 *
 * @author ziyao
 */
@Data
public class AuthorizationRecordDTO implements EntityDTO<AuthorizationRecord>, Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer id;
    /**
     *
     */
    private String principal;

    public AuthorizationRecord convert() {
        return AuthorizationRecordConvertor.INSTANCE.convert(this);
    }
}
