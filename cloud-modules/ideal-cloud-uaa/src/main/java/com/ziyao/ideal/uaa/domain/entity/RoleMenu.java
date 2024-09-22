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
 * 角色菜单表
 * </p>
 *
 * @author ziyao
 */
@Getter
@Setter
@Builder
@Entity(name = "role_menu")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色菜单表")
public class RoleMenu implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * appId:系统id
     */
    @Id
    @Schema(description = "系统id")
    private Integer appId;

    /**
     * roleId:角色id
     */
    @Schema(description = "角色id")
    private Integer roleId;

    /**
     * menuId:菜单id
     */
    @Schema(description = "菜单id")
    private Integer menuId;

    /**
     * createdAt:创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;


    public static class Builder {

        private final RoleMenu roleMenu = new RoleMenu();

        public Builder appId(Integer appId) {
            this.roleMenu.appId = appId;
            return this;
        }

        public Builder roleId(Integer roleId) {
            this.roleMenu.roleId = roleId;
            return this;
        }

        public Builder menuId(Integer menuId) {
            this.roleMenu.menuId = menuId;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.roleMenu.createdAt = createdAt;
            return this;
        }


        public RoleMenu build() {
            return this.roleMenu;
        }
    }
}
