package com.ziyao.ideal.uua.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author zhangziyao
 */
@Getter
@Setter
@TableName("user")
@Entity(name = "user")
public class User implements Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @Id
    @TableId("id")
    private Integer id;

    /**
     * 用户账号
     */
    @TableField("username")
    private String username;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 用户凭证
     */
    @TableField("password")
    private String password;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 姓名
     */
    @TableField("id_card_name")
    private String idCardName;

    /**
     * 身份证号
     */
    @TableField("id_card_no")
    private String idCardNo;

    /**
     * 性别（M: 男, F: 女, O: 其他）
     */
    @TableField("gender")
    private String gender;

    /**
     * 出生日期
     */
    @TableField("date_of_birth")
    private LocalDateTime dateOfBirth;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 账号状态 1 正常 2 失效 3 禁用 4 锁定
     */
    @TableField("status")
    private Integer status;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 删除状态 0正常 1 删除
     */
    @TableField("deleted")

    private Integer deleted;

    /**
     * 最后登录时间
     */
    @TableField("last_login")
    private LocalDateTime lastLogin;

    /**
     * 创建人id
     */
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private Integer createdBy;

    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 修改人id
     */
    @TableField(value = "modified_by", fill = FieldFill.UPDATE)
    private Integer modifiedBy;

    /**
     * 修改时间
     */
    @TableField(value = "modified_at", fill = FieldFill.UPDATE)
    private LocalDateTime modifiedAt;


}
