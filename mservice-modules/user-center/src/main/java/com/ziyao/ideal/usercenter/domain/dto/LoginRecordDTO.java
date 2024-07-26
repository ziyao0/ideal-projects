package com.ziyao.ideal.usercenter.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.web.orm.EntityDTO;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.usercenter.domain.entity.LoginRecord;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 登录记录表
 * </p>
 *
 * @author zhangziyao
 */
@Data
public class LoginRecordDTO implements EntityDTO<LoginRecord>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
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
                // 更新时间
                .eq(Objects.nonNull(updatedAt), LoginRecord::getUpdatedAt, updatedAt)
                ;
    }

    @Override
    public LoginRecord getEntity() {
        return new LoginRecord();
    }
}
