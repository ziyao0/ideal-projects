package com.ziyao.ideal.uua.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.web.orm.EntityDTO;
import com.ziyao.ideal.uua.domain.entity.LoginRecord;
import com.ziyao.ideal.uua.domain.mapstruct.LoginRecordMapstruct;
import lombok.Data;
import java.util.Objects;
import com.ziyao.ideal.core.Strings;


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
public class LoginRecordDTO implements EntityDTO<LoginRecord>, Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 
     */
    private String username;
    /**
     * 登录方式（例如：密码、指纹、面部识别、OAuth等）
     */
    private String loginMethod;
    /**
     * 登录ip
     */
    private String loginIp;
    /**
     * 
     */
    private LocalDateTime loginTime;
    /**
     * 推出时间
     */
    private LocalDateTime logoutTime;
    /**
     * 设备信息
     */
    private String deviceInfo;
    /**
     * 操作系统信息
     */
    private String osInfo;
    /**
     * 浏览器信息
     */
    private String browserInfo;
    /**
     * 地理位置
     */
    private String location;
    /**
     * 登录状态 成功SUCCESS, 失败FAILURE
     */
    private String status;
    /**
     * 失败原因
     */
    private String failureReason;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 组装查询条件，可根据具体情况做出修改
     *
     * @see LambdaQueryWrapper
     */
    public LambdaQueryWrapper<LoginRecord> initWrapper() {

        return Wrappers.lambdaQuery(LoginRecord.class)
                // 用户ID
                .eq(Objects.nonNull(userId), LoginRecord::getUserId, userId)
                // 
                .likeRight(Strings.hasLength(username), LoginRecord::getUsername, username)
                // 登录方式（例如：密码、指纹、面部识别、OAuth等）
                .likeRight(Strings.hasLength(loginMethod), LoginRecord::getLoginMethod, loginMethod)
                // 登录ip
                .likeRight(Strings.hasLength(loginIp), LoginRecord::getLoginIp, loginIp)
                // 
                .eq(Objects.nonNull(loginTime), LoginRecord::getLoginTime, loginTime)
                // 推出时间
                .eq(Objects.nonNull(logoutTime), LoginRecord::getLogoutTime, logoutTime)
                // 设备信息
                .likeRight(Strings.hasLength(deviceInfo), LoginRecord::getDeviceInfo, deviceInfo)
                // 操作系统信息
                .likeRight(Strings.hasLength(osInfo), LoginRecord::getOsInfo, osInfo)
                // 浏览器信息
                .likeRight(Strings.hasLength(browserInfo), LoginRecord::getBrowserInfo, browserInfo)
                // 地理位置
                .likeRight(Strings.hasLength(location), LoginRecord::getLocation, location)
                // 登录状态 成功SUCCESS, 失败FAILURE
                .likeRight(Strings.hasLength(status), LoginRecord::getStatus, status)
                // 失败原因
                .likeRight(Strings.hasLength(failureReason), LoginRecord::getFailureReason, failureReason)
                // 创建时间
                .eq(Objects.nonNull(createdAt), LoginRecord::getCreatedAt, createdAt)
                // 更新时间
                .eq(Objects.nonNull(updatedAt), LoginRecord::getUpdatedAt, updatedAt)
                ;
    }

    public LoginRecord of() {
        return LoginRecordMapstruct.INSTANCE.of(this);
    }
}
