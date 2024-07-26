package com.ziyao.ideal.usercenter.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.web.orm.EntityDTO;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.usercenter.domain.entity.Role;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author zhangziyao
 */
@Data
public class RoleDTO implements EntityDTO<Role>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
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
                .likeRight(Strings.hasLength(roleName), Role::getName, roleName)
                // 角色编码
                .likeRight(Strings.hasLength(roleCode), Role::getRole, roleCode)
                // 角色描述
                .likeRight(Strings.hasLength(description), Role::getDescription, description)
                ;
    }

    @Override
    public Role getEntity() {
        return new Role();
    }
}
