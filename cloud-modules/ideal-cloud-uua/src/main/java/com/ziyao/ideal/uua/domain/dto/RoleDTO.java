package com.ziyao.ideal.uua.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.web.orm.EntityDTO;
import com.ziyao.ideal.uua.domain.entity.Role;
import com.ziyao.ideal.uua.domain.mapstruct.RoleMapstruct;
import lombok.Data;
import java.util.Objects;
import com.ziyao.ideal.core.Strings;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author ziyao
 */
@Data
public class RoleDTO implements EntityDTO<Role>, Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String role;
    /**
     * 
     */
    private Integer type;
    /**
     * 角色类别 1 权限角色 2 组织角色
     */
    private Integer category;
    /**
     * 
     */
    private String accessLevel;
    /**
     * 1 启用 0禁用
     */
    private Boolean active;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 创建人id
     */
    private Integer createdBy;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 修改人id
     */
    private Integer modifiedBy;
    /**
     * 修改时间
     */
    private LocalDateTime modifiedAt;

    /**
     * 组装查询条件，可根据具体情况做出修改
     *
     * @see LambdaQueryWrapper
     */
    public LambdaQueryWrapper<Role> initWrapper() {

        return Wrappers.lambdaQuery(Role.class)
                // 角色名称
                .likeRight(Strings.hasLength(name), Role::getName, name)
                // 角色编码
                .likeRight(Strings.hasLength(role), Role::getRole, role)
                // 
                .eq(Objects.nonNull(type), Role::getType, type)
                // 角色类别 1 权限角色 2 组织角色
                .eq(Objects.nonNull(category), Role::getCategory, category)
                // 
                .likeRight(Strings.hasLength(accessLevel), Role::getAccessLevel, accessLevel)
                // 1 启用 0禁用
                .eq(Objects.nonNull(active), Role::getActive, active)
                // 角色描述
                .likeRight(Strings.hasLength(description), Role::getDescription, description)
                // 创建人id
                .eq(Objects.nonNull(createdBy), Role::getCreatedBy, createdBy)
                // 创建时间
                .eq(Objects.nonNull(createdAt), Role::getCreatedAt, createdAt)
                // 修改人id
                .eq(Objects.nonNull(modifiedBy), Role::getModifiedBy, modifiedBy)
                // 修改时间
                .eq(Objects.nonNull(modifiedAt), Role::getModifiedAt, modifiedAt)
                ;
    }

    public Role of() {
        return RoleMapstruct.INSTANCE.of(this);
    }
}
