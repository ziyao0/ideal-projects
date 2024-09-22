package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.entity.AuthorizationRecord;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 授权记录表
 * </p>
 *
 * @author ziyao
 */
@Data
@Schema(description = "授权记录表")
public class AuthorizationRecordDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @Schema(description = "")
    private Integer id;

    /**
     *
     */
    @Schema(description = "")
    private String principal;

    public AuthorizationRecord toEntity() {
        AuthorizationRecord authorizationRecord = new AuthorizationRecord();
        authorizationRecord.setId(this.id);
        authorizationRecord.setPrincipal(this.principal);
        return authorizationRecord;
    }
}
