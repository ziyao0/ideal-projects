package com.ziyao.ideal.uua.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author zhangziyao
 */
@Getter
@Setter
@TableName("role_menu")
@Entity(name = "role_menu")
public class RoleMenu implements Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 系统id
     */
    @Id
    @TableId("app_id")
    private Integer appId;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 菜单id
     */
    private Integer menuId;

    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;


}
