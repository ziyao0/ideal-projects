package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.entity.LoginRecord;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 登录记录表
 * </p>
 *
 * @author ziyao
 */
@Data
@Schema(description = "登录记录表")
public class LoginRecordDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @Schema(description = "")
    private Integer id;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Integer userId;

    /**
     *
     */
    @Schema(description = "")
    private String username;

    /**
     * 登录方式（例如：密码、指纹、面部识别、OAuth等）
     */
    @Schema(description = "登录方式（例如：密码、指纹、面部识别、OAuth等）")
    private String loginMethod;

    /**
     * 登录ip
     */
    @Schema(description = "登录ip")
    private String loginIp;

    /**
     *
     */
    @Schema(description = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;
    /**
     * start time for
     */
    @Schema(description = "-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startLoginTime;

    /**
     * end time for
     */
    @Schema(description = "-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endLoginTime;

    /**
     * 推出时间
     */
    @Schema(description = "推出时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime logoutTime;
    /**
     * start time for 推出时间
     */
    @Schema(description = "推出时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startLogoutTime;

    /**
     * end time for 推出时间
     */
    @Schema(description = "推出时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endLogoutTime;

    /**
     * 设备信息
     */
    @Schema(description = "设备信息")
    private String deviceInfo;

    /**
     * 操作系统信息
     */
    @Schema(description = "操作系统信息")
    private String osInfo;

    /**
     * 浏览器信息
     */
    @Schema(description = "浏览器信息")
    private String browserInfo;

    /**
     * 地理位置
     */
    @Schema(description = "地理位置")
    private String location;

    /**
     * 登录状态 成功SUCCESS, 失败FAILURE
     */
    @Schema(description = "登录状态 成功SUCCESS, 失败FAILURE")
    private String status;

    /**
     * 失败原因
     */
    @Schema(description = "失败原因")
    private String failureReason;

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

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    /**
     * start time for 更新时间
     */
    @Schema(description = "更新时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startUpdatedAt;

    /**
     * end time for 更新时间
     */
    @Schema(description = "更新时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endUpdatedAt;

    public LoginRecord toEntity() {
        LoginRecord loginRecord = new LoginRecord();
        loginRecord.setId(this.id);
        loginRecord.setUserId(this.userId);
        loginRecord.setUsername(this.username);
        loginRecord.setLoginMethod(this.loginMethod);
        loginRecord.setLoginIp(this.loginIp);
        loginRecord.setLoginTime(this.loginTime);
        loginRecord.setLogoutTime(this.logoutTime);
        loginRecord.setDeviceInfo(this.deviceInfo);
        loginRecord.setOsInfo(this.osInfo);
        loginRecord.setBrowserInfo(this.browserInfo);
        loginRecord.setLocation(this.location);
        loginRecord.setStatus(this.status);
        loginRecord.setFailureReason(this.failureReason);
        loginRecord.setCreatedAt(this.createdAt);
        loginRecord.setUpdatedAt(this.updatedAt);
        return loginRecord;
    }
}
