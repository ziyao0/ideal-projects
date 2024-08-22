package com.ziyao.ideal.uaa.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

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
@Getter
@Setter
@Builder
@Entity(name = "role")
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * id:角色id
     */
    @Id
    private Integer id;

    /**
     * name:角色名称
     */
    private String name;

    /**
     * role:角色编码
     */
    private String role;

    /**
     * type:
     */
    private Integer type;

    /**
     * category:角色类别 1 权限角色 2 组织角色
     */
    private Integer category;

    /**
     * accessLevel:
     */
    private String accessLevel;

    /**
     * active:1 启用 0禁用
     */
    private Boolean active;

    /**
     * description:角色描述
     */
    private String description;

    /**
     * createdBy:创建人id
     */
    private Integer createdBy;

    /**
     * createdAt:创建时间
     */
    private LocalDateTime createdAt;

    /**
     * modifiedBy:修改人id
     */
    private Integer modifiedBy;

    /**
     * modifiedAt:修改时间
     */
    private LocalDateTime modifiedAt;
}
