package com.ziyao.ideal.uua.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.web.orm.EntityDTO;
import com.ziyao.ideal.uua.domain.entity.AuthorizationRecord;
import com.ziyao.ideal.uua.domain.mapstruct.AuthorizationRecordMapstruct;
import lombok.Data;
import java.util.Objects;
import com.ziyao.ideal.core.Strings;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 授权记录表
 * </p>
 *
 * @author ziyao
 */
@Data
public class AuthorizationRecordDTO implements EntityDTO<AuthorizationRecord>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private String principal;

    /**
     * 组装查询条件，可根据具体情况做出修改
     *
     * @see LambdaQueryWrapper
     */
    public LambdaQueryWrapper<AuthorizationRecord> initWrapper() {

        return Wrappers.lambdaQuery(AuthorizationRecord.class)
                // 
                .likeRight(Strings.hasLength(principal), AuthorizationRecord::getPrincipal, principal)
                ;
    }

    public AuthorizationRecord of() {
        return AuthorizationRecordMapstruct.INSTANCE.of(this);
    }
}
