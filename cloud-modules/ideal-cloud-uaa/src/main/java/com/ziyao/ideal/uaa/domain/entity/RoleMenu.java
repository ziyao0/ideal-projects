package com.ziyao.ideal.uaa.domain.entity;

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
public class RoleMenu implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * appId:系统id
     */
    @Id
    private Integer appId;

    /**
     * roleId:角色id
     */
    @Id
    private Integer roleId;

    /**
     * menuId:菜单id
     */
    @Id
    private Integer menuId;

    /**
     * createdAt:创建时间
     */
    private LocalDateTime createdAt;
}
