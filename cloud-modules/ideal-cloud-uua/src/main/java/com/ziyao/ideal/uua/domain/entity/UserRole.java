package com.ziyao.ideal.uua.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangziyao
 */
@Getter
@Setter
@TableName("user_role")
@Entity(name = "user_role")
public class UserRole implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Id
    private Integer userId;

    private Integer roleId;

    @TableField("role")
    private String role;

    @TableField("category")
    private Integer category;

    @TableField("access_level")
    private String accessLevel;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;


}
