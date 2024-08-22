package com.ziyao.ideal.uua.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 登录记录表
 * </p>
 *
 * @author ziyao
 */
@Getter
@Setter
@Builder
@Entity(name = "login_record")
@NoArgsConstructor
@AllArgsConstructor
public class LoginRecord implements Serializable {


    private static final long serialVersionUID = 1L;


    /**
     * id:
     */
    @Id
    private Integer id;

    /**
     * userId:用户ID
     */
    private Integer userId;

    /**
     * username:
     */
    private String username;

    /**
     * loginMethod:登录方式（例如：密码、指纹、面部识别、OAuth等）
     */
    private String loginMethod;

    /**
     * loginIp:登录ip
     */
    private String loginIp;

    /**
     * loginTime:
     */
    private LocalDateTime loginTime;

    /**
     * logoutTime:推出时间
     */
    private LocalDateTime logoutTime;

    /**
     * deviceInfo:设备信息
     */
    private String deviceInfo;

    /**
     * osInfo:操作系统信息
     */
    private String osInfo;

    /**
     * browserInfo:浏览器信息
     */
    private String browserInfo;

    /**
     * location:地理位置
     */
    private String location;

    /**
     * status:登录状态 成功SUCCESS, 失败FAILURE
     */
    private String status;

    /**
     * failureReason:失败原因
     */
    private String failureReason;

    /**
     * createdAt:创建时间
     */
    private LocalDateTime createdAt;

    /**
     * updatedAt:更新时间
     */
    private LocalDateTime updatedAt;
}
