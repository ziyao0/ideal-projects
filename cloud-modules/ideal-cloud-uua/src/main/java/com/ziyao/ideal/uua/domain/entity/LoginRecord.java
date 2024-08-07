package com.ziyao.ideal.uua.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 登录记录表
 * </p>
 *
 * @author zhangziyao
 */
@Getter
@Setter
@TableName("login_record")
@Entity(name = "login_record")
public class LoginRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @TableId("id")
    private Integer id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    @TableField("username")
    private String username;

    /**
     * 登录方式（例如：密码、指纹、面部识别、OAuth等）
     */
    @TableField("login_method")
    private String loginMethod;

    /**
     * 登录ip
     */
    @TableField("login_ip")
    private String loginIp;

    @TableField("login_time")
    private LocalDateTime loginTime;

    /**
     * 推出时间
     */
    @TableField("logout_time")
    private LocalDateTime logoutTime;

    /**
     * 设备信息
     */
    @TableField("device_info")
    private String deviceInfo;

    /**
     * 操作系统信息
     */
    @TableField("os_info")
    private String osInfo;

    /**
     * 浏览器信息
     */
    @TableField("browser_info")
    private String browserInfo;

    /**
     * 地理位置
     */
    @TableField("location")
    private String location;

    /**
     * 登录状态 成功SUCCESS, 失败FAILURE
     */
    @TableField("status")
    private String status;

    /**
     * 失败原因
     */
    @TableField("failure_reason")
    private String failureReason;

    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField("updated_at")
    private LocalDateTime updatedAt;


}
