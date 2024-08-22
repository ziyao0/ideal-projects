package com.ziyao.ideal.uua.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
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


    private static final long serialVersionUID = 1L;


    /**
     * appId:系统id
     */
    @Id
    private Integer appId;

    /**
     * roleId:角色id
     */
    private Integer roleId;

    /**
     * menuId:菜单id
     */
    private Integer menuId;

    /**
     * createdAt:创建时间
     */
    private LocalDateTime createdAt;
}
