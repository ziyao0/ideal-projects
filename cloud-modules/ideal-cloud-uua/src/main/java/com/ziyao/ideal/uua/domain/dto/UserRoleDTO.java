package com.ziyao.ideal.uua.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.web.orm.EntityDTO;
import com.ziyao.ideal.uua.domain.entity.UserRole;
import com.ziyao.ideal.uua.domain.mapstruct.UserRoleMapstruct;
import lombok.Data;
import java.util.Objects;
import com.ziyao.ideal.core.Strings;


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

    /**
     * 组装查询条件，可根据具体情况做出修改
     *
     * @see LambdaQueryWrapper
     */
    public LambdaQueryWrapper<UserRole> initWrapper() {

        return Wrappers.lambdaQuery(UserRole.class)
                // 
                .likeRight(Strings.hasLength(role), UserRole::getRole, role)
                // 
                .eq(Objects.nonNull(category), UserRole::getCategory, category)
                // 
                .likeRight(Strings.hasLength(accessLevel), UserRole::getAccessLevel, accessLevel)
                // 
                .eq(Objects.nonNull(createdAt), UserRole::getCreatedAt, createdAt)
                ;
    }

    public UserRole of() {
        return UserRoleMapstruct.INSTANCE.of(this);
    }
}
