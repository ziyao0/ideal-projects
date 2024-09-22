package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.entity.RoleMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
@Data
@Schema(description = "角色菜单表")
public class RoleMenuDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 系统id
     */
    @Schema(description = "系统id")
    private Integer appId;

    /**
     * 角色id
     */
    @Schema(description = "角色id")
    private Integer roleId;

    /**
     * 菜单id
     */
    @Schema(description = "菜单id")
    private Integer menuId;

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

    public RoleMenu toEntity() {
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setAppId(this.appId);
        roleMenu.setRoleId(this.roleId);
        roleMenu.setMenuId(this.menuId);
        roleMenu.setCreatedAt(this.createdAt);
        return roleMenu;
    }
}
