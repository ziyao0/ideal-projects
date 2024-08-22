package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.convertor.LoginRecordConvertor;
import com.ziyao.ideal.uaa.domain.entity.LoginRecord;
import com.ziyao.ideal.web.orm.EntityDTO;
import lombok.Data;

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
public class LoginRecordDTO implements EntityDTO<LoginRecord>, Serializable {

    @Serial
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

    public LoginRecord convert() {
        return LoginRecordConvertor.INSTANCE.convert(this);
    }
}
