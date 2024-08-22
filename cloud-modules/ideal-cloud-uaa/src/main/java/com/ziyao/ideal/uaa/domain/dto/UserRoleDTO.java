package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.convertor.UserRoleConvertor;
import com.ziyao.ideal.uaa.domain.entity.UserRole;
import com.ziyao.ideal.web.orm.EntityDTO;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author ziyao
 */
@Data
public class UserRoleDTO implements EntityDTO<UserRole>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer userId;
    /**
     *
     */
    private Integer roleId;
    /**
     *
     */
    private String role;
    /**
     *
     */
    private Integer category;
    /**
     *
     */
    private String accessLevel;
    /**
     *
     */
    private LocalDateTime createdAt;

    public UserRole convert() {
        return UserRoleConvertor.INSTANCE.convert(this);
    }
}
