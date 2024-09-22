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
 * 异常信息记录表
 * </p>
 *
 * @author ziyao
 */
@Getter
@Setter
@Builder
@Entity(name = "error_record")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "异常信息记录表")
public class ErrorRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id:唯一标识符，自增
     */
    @Id
    @Schema(description = "唯一标识符，自增")
    private Long id;

    /**
     * exceptionType:异常类型，例如 NullPointerException、SQLException 等
     */
    @Schema(description = "异常类型，例如 NullPointerException、SQLException 等")
    private String exceptionType;

    /**
     * exceptionMessage:异常消息，详细描述异常信息
     */
    @Schema(description = "异常消息，详细描述异常信息")
    private String exceptionMessage;

    /**
     * stackTrace:异常的堆栈跟踪信息
     */
    @Schema(description = "异常的堆栈跟踪信息")
    private String stackTrace;

    /**
     * timestamp:异常发生的时间戳
     */
    @Schema(description = "异常发生的时间戳")
    private LocalDateTime timestamp;

    /**
     * module:异常发生的模块或功能
     */
    @Schema(description = "异常发生的模块或功能")
    private String module;

    /**
     * serverName:服务器名称
     */
    @Schema(description = "服务器名称")
    private String serverName;

    /**
     * serverIp:服务器 IP 地址
     */
    @Schema(description = "服务器 IP 地址")
    private String serverIp;

    /**
     * requestUrl:异常发生时的请求 URL
     */
    @Schema(description = "异常发生时的请求 URL")
    private String requestUrl;

    /**
     * userId:触发异常的用户 ID（如果有的话）
     */
    @Schema(description = "触发异常的用户 ID（如果有的话）")
    private Long userId;

    /**
     * userAgent:发起请求的用户代理字符串（例如浏览器信息）
     */
    @Schema(description = "发起请求的用户代理字符串（例如浏览器信息）")
    private String userAgent;

    /**
     * requestParams:请求参数，以 JSON 格式存储（如果适用）
     */
    @Schema(description = "请求参数，以 JSON 格式存储（如果适用）")
    private String requestParams;

    /**
     * httpMethod:HTTP 方法，例如 GET、POST 等
     */
    @Schema(description = "HTTP 方法，例如 GET、POST 等")
    private String httpMethod;

    /**
     * sessionId:用户会话 ID（如果有的话）
     */
    @Schema(description = "用户会话 ID（如果有的话）")
    private String sessionId;

    /**
     * transactionId:事务 ID（如果有的话）
     */
    @Schema(description = "事务 ID（如果有的话）")
    private String transactionId;

    /**
     * errorCode:错误代码（如果系统定义了错误代码）
     */
    @Schema(description = "错误代码（如果系统定义了错误代码）")
    private String errorCode;

    /**
     * isHandled:是否已处理异常
     */
    @Schema(description = "是否已处理异常")
    private Boolean isHandled;

    /**
     * handledBy:处理异常的人员或系统组件
     */
    @Schema(description = "处理异常的人员或系统组件")
    private String handledBy;

    /**
     * resolution:异常解决方案或备注
     */
    @Schema(description = "异常解决方案或备注")
    private String resolution;

    /**
     * extras:其他额外信息，如上下文信息、相关日志等
     */
    @Schema(description = "其他额外信息，如上下文信息、相关日志等")
    private String extras;


    public static class Builder {

        private final ErrorRecord errorRecord = new ErrorRecord();

        public Builder id(Long id) {
            this.errorRecord.id = id;
            return this;
        }

        public Builder exceptionType(String exceptionType) {
            this.errorRecord.exceptionType = exceptionType;
            return this;
        }

        public Builder exceptionMessage(String exceptionMessage) {
            this.errorRecord.exceptionMessage = exceptionMessage;
            return this;
        }

        public Builder stackTrace(String stackTrace) {
            this.errorRecord.stackTrace = stackTrace;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.errorRecord.timestamp = timestamp;
            return this;
        }

        public Builder module(String module) {
            this.errorRecord.module = module;
            return this;
        }

        public Builder serverName(String serverName) {
            this.errorRecord.serverName = serverName;
            return this;
        }

        public Builder serverIp(String serverIp) {
            this.errorRecord.serverIp = serverIp;
            return this;
        }

        public Builder requestUrl(String requestUrl) {
            this.errorRecord.requestUrl = requestUrl;
            return this;
        }

        public Builder userId(Long userId) {
            this.errorRecord.userId = userId;
            return this;
        }

        public Builder userAgent(String userAgent) {
            this.errorRecord.userAgent = userAgent;
            return this;
        }

        public Builder requestParams(String requestParams) {
            this.errorRecord.requestParams = requestParams;
            return this;
        }

        public Builder httpMethod(String httpMethod) {
            this.errorRecord.httpMethod = httpMethod;
            return this;
        }

        public Builder sessionId(String sessionId) {
            this.errorRecord.sessionId = sessionId;
            return this;
        }

        public Builder transactionId(String transactionId) {
            this.errorRecord.transactionId = transactionId;
            return this;
        }

        public Builder errorCode(String errorCode) {
            this.errorRecord.errorCode = errorCode;
            return this;
        }

        public Builder isHandled(Boolean isHandled) {
            this.errorRecord.isHandled = isHandled;
            return this;
        }

        public Builder handledBy(String handledBy) {
            this.errorRecord.handledBy = handledBy;
            return this;
        }

        public Builder resolution(String resolution) {
            this.errorRecord.resolution = resolution;
            return this;
        }

        public Builder extras(String extras) {
            this.errorRecord.extras = extras;
            return this;
        }


        public ErrorRecord build() {
            return this.errorRecord;
        }
    }
}
