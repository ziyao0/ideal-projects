package com.ziyao.ideal.uaa.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

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
@Getter
@Setter
@Builder
@Entity(name = "login_record")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "登录记录表")
public class LoginRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id:
     */
    @Id
    @Schema(description = "")
    private Integer id;

    /**
     * userId:用户ID
     */
    @Schema(description = "用户ID")
    private Integer userId;

    /**
     * username:
     */
    @Schema(description = "")
    private String username;

    /**
     * loginMethod:登录方式（例如：密码、指纹、面部识别、OAuth等）
     */
    @Schema(description = "登录方式（例如：密码、指纹、面部识别、OAuth等）")
    private String loginMethod;

    /**
     * loginIp:登录ip
     */
    @Schema(description = "登录ip")
    private String loginIp;

    /**
     * loginTime:
     */
    @Schema(description = "")
    private LocalDateTime loginTime;

    /**
     * logoutTime:推出时间
     */
    @Schema(description = "推出时间")
    private LocalDateTime logoutTime;

    /**
     * deviceInfo:设备信息
     */
    @Schema(description = "设备信息")
    private String deviceInfo;

    /**
     * osInfo:操作系统信息
     */
    @Schema(description = "操作系统信息")
    private String osInfo;

    /**
     * browserInfo:浏览器信息
     */
    @Schema(description = "浏览器信息")
    private String browserInfo;

    /**
     * location:地理位置
     */
    @Schema(description = "地理位置")
    private String location;

    /**
     * status:登录状态 成功SUCCESS, 失败FAILURE
     */
    @Schema(description = "登录状态 成功SUCCESS, 失败FAILURE")
    private String status;

    /**
     * failureReason:失败原因
     */
    @Schema(description = "失败原因")
    private String failureReason;

    /**
     * createdAt:创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    /**
     * updatedAt:更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;


    public static class Builder {

        private final LoginRecord loginRecord = new LoginRecord();

        public Builder id(Integer id) {
            this.loginRecord.id = id;
            return this;
        }

        public Builder userId(Integer userId) {
            this.loginRecord.userId = userId;
            return this;
        }

        public Builder username(String username) {
            this.loginRecord.username = username;
            return this;
        }

        public Builder loginMethod(String loginMethod) {
            this.loginRecord.loginMethod = loginMethod;
            return this;
        }

        public Builder loginIp(String loginIp) {
            this.loginRecord.loginIp = loginIp;
            return this;
        }

        public Builder loginTime(LocalDateTime loginTime) {
            this.loginRecord.loginTime = loginTime;
            return this;
        }

        public Builder logoutTime(LocalDateTime logoutTime) {
            this.loginRecord.logoutTime = logoutTime;
            return this;
        }

        public Builder deviceInfo(String deviceInfo) {
            this.loginRecord.deviceInfo = deviceInfo;
            return this;
        }

        public Builder osInfo(String osInfo) {
            this.loginRecord.osInfo = osInfo;
            return this;
        }

        public Builder browserInfo(String browserInfo) {
            this.loginRecord.browserInfo = browserInfo;
            return this;
        }

        public Builder location(String location) {
            this.loginRecord.location = location;
            return this;
        }

        public Builder status(String status) {
            this.loginRecord.status = status;
            return this;
        }

        public Builder failureReason(String failureReason) {
            this.loginRecord.failureReason = failureReason;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.loginRecord.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.loginRecord.updatedAt = updatedAt;
            return this;
        }


        public LoginRecord build() {
            return this.loginRecord;
        }
    }
}
