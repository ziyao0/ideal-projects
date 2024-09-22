package com.ziyao.ideal.uaa.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "角色表")
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id:角色id
     */
    @Id
    @Schema(description = "角色id")
    private Integer id;

    /**
     * name:角色名称
     */
    @Schema(description = "角色名称")
    private String name;

    /**
     * role:角色编码
     */
    @Schema(description = "角色编码")
    private String role;

    /**
     * type:
     */
    @Schema(description = "")
    private Integer type;

    /**
     * category:角色类别 1 权限角色 2 组织角色
     */
    @Schema(description = "角色类别 1 权限角色 2 组织角色")
    private Integer category;

    /**
     * accessLevel:
     */
    @Schema(description = "")
    private String accessLevel;

    /**
     * active:1 启用 0禁用
     */
    @Schema(description = "1 启用 0禁用")
    private Boolean active;

    /**
     * description:角色描述
     */
    @Schema(description = "角色描述")
    private String description;

    /**
     * createdBy:创建人id
     */
    @Schema(description = "创建人id")
    private Integer createdBy;

    /**
     * createdAt:创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    /**
     * modifiedBy:修改人id
     */
    @Schema(description = "修改人id")
    private Integer modifiedBy;

    /**
     * modifiedAt:修改时间
     */
    @Schema(description = "修改时间")
    private LocalDateTime modifiedAt;


    public static class Builder {

        private final Role role = new Role();

        public Builder id(Integer id) {
            this.role.id = id;
            return this;
        }

        public Builder name(String name) {
            this.role.name = name;
            return this;
        }

        public Builder role(String role) {
            this.role.role = role;
            return this;
        }

        public Builder type(Integer type) {
            this.role.type = type;
            return this;
        }

        public Builder category(Integer category) {
            this.role.category = category;
            return this;
        }

        public Builder accessLevel(String accessLevel) {
            this.role.accessLevel = accessLevel;
            return this;
        }

        public Builder active(Boolean active) {
            this.role.active = active;
            return this;
        }

        public Builder description(String description) {
            this.role.description = description;
            return this;
        }

        public Builder createdBy(Integer createdBy) {
            this.role.createdBy = createdBy;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.role.createdAt = createdAt;
            return this;
        }

        public Builder modifiedBy(Integer modifiedBy) {
            this.role.modifiedBy = modifiedBy;
            return this;
        }

        public Builder modifiedAt(LocalDateTime modifiedAt) {
            this.role.modifiedAt = modifiedAt;
            return this;
        }


        public Role build() {
            return this.role;
        }
    }
}
