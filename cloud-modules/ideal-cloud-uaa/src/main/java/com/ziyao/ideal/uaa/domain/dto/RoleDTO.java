package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
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
@Schema(description = "角色表")
public class RoleDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 角色id
     */
    @Schema(description = "角色id")
    private Integer id;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;

    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    private String role;

    /**
     *
     */
    @Schema(description = "")
    private Integer type;

    /**
     * 角色类别 1 权限角色 2 组织角色
     */
    @Schema(description = "角色类别 1 权限角色 2 组织角色")
    private Integer category;

    /**
     *
     */
    @Schema(description = "")
    private String accessLevel;

    /**
     * 1 启用 0禁用
     */
    @Schema(description = "1 启用 0禁用")
    private Boolean active;

    /**
     * 角色描述
     */
    @Schema(description = "角色描述")
    private String description;

    /**
     * 创建人id
     */
    @Schema(description = "创建人id")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    /**
     * start time for 创建时间
     */
    @Schema(description = "创建时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startCreatedAt;

    /**
     * end time for 创建时间
     */
    @Schema(description = "创建时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endCreatedAt;

    /**
     * 修改人id
     */
    @Schema(description = "修改人id")
    private Integer modifiedBy;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
    /**
     * start time for 修改时间
     */
    @Schema(description = "修改时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startModifiedAt;

    /**
     * end time for 修改时间
     */
    @Schema(description = "修改时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endModifiedAt;

    public Role toEntity() {
        Role role = new Role();
        role.setId(this.id);
        role.setName(this.name);
        role.setRole(this.role);
        role.setType(this.type);
        role.setCategory(this.category);
        role.setAccessLevel(this.accessLevel);
        role.setActive(this.active);
        role.setDescription(this.description);
        role.setCreatedBy(this.createdBy);
        role.setCreatedAt(this.createdAt);
        role.setModifiedBy(this.modifiedBy);
        role.setModifiedAt(this.modifiedAt);
        return role;
    }
}
