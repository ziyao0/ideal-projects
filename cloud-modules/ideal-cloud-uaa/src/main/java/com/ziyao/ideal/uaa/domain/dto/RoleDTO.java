package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.convertor.RoleConvertor;
import com.ziyao.ideal.uaa.domain.entity.Role;
import com.ziyao.ideal.web.orm.EntityDTO;
import lombok.Data;

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

    public Role convert() {
        return RoleConvertor.INSTANCE.convert(this);
    }
}
