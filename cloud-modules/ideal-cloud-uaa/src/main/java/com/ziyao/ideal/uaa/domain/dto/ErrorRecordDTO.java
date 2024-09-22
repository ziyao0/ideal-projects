package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.entity.ErrorRecord;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
@Data
@Schema(description = "异常信息记录表")
public class ErrorRecordDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 唯一标识符，自增
     */
    @Schema(description = "唯一标识符，自增")
    private Long id;

    /**
     * 异常类型，例如 NullPointerException、SQLException 等
     */
    @Schema(description = "异常类型，例如 NullPointerException、SQLException 等")
    private String exceptionType;

    /**
     * 异常消息，详细描述异常信息
     */
    @Schema(description = "异常消息，详细描述异常信息")
    private String exceptionMessage;

    /**
     * 异常的堆栈跟踪信息
     */
    @Schema(description = "异常的堆栈跟踪信息")
    private String stackTrace;

    /**
     * 异常发生的时间戳
     */
    @Schema(description = "异常发生的时间戳")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    /**
     * start time for 异常发生的时间戳
     */
    @Schema(description = "异常发生的时间戳-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTimestamp;

    /**
     * end time for 异常发生的时间戳
     */
    @Schema(description = "异常发生的时间戳-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTimestamp;

    /**
     * 异常发生的模块或功能
     */
    @Schema(description = "异常发生的模块或功能")
    private String module;

    /**
     * 服务器名称
     */
    @Schema(description = "服务器名称")
    private String serverName;

    /**
     * 服务器 IP 地址
     */
    @Schema(description = "服务器 IP 地址")
    private String serverIp;

    /**
     * 异常发生时的请求 URL
     */
    @Schema(description = "异常发生时的请求 URL")
    private String requestUrl;

    /**
     * 触发异常的用户 ID（如果有的话）
     */
    @Schema(description = "触发异常的用户 ID（如果有的话）")
    private Long userId;

    /**
     * 发起请求的用户代理字符串（例如浏览器信息）
     */
    @Schema(description = "发起请求的用户代理字符串（例如浏览器信息）")
    private String userAgent;

    /**
     * 请求参数，以 JSON 格式存储（如果适用）
     */
    @Schema(description = "请求参数，以 JSON 格式存储（如果适用）")
    private String requestParams;

    /**
     * HTTP 方法，例如 GET、POST 等
     */
    @Schema(description = "HTTP 方法，例如 GET、POST 等")
    private String httpMethod;

    /**
     * 用户会话 ID（如果有的话）
     */
    @Schema(description = "用户会话 ID（如果有的话）")
    private String sessionId;

    /**
     * 事务 ID（如果有的话）
     */
    @Schema(description = "事务 ID（如果有的话）")
    private String transactionId;

    /**
     * 错误代码（如果系统定义了错误代码）
     */
    @Schema(description = "错误代码（如果系统定义了错误代码）")
    private String errorCode;

    /**
     * 是否已处理异常
     */
    @Schema(description = "是否已处理异常")
    private Boolean isHandled;

    /**
     * 处理异常的人员或系统组件
     */
    @Schema(description = "处理异常的人员或系统组件")
    private String handledBy;

    /**
     * 异常解决方案或备注
     */
    @Schema(description = "异常解决方案或备注")
    private String resolution;

    /**
     * 其他额外信息，如上下文信息、相关日志等
     */
    @Schema(description = "其他额外信息，如上下文信息、相关日志等")
    private String extras;

    public ErrorRecord toEntity() {
        ErrorRecord errorRecord = new ErrorRecord();
        errorRecord.setId(this.id);
        errorRecord.setExceptionType(this.exceptionType);
        errorRecord.setExceptionMessage(this.exceptionMessage);
        errorRecord.setStackTrace(this.stackTrace);
        errorRecord.setTimestamp(this.timestamp);
        errorRecord.setModule(this.module);
        errorRecord.setServerName(this.serverName);
        errorRecord.setServerIp(this.serverIp);
        errorRecord.setRequestUrl(this.requestUrl);
        errorRecord.setUserId(this.userId);
        errorRecord.setUserAgent(this.userAgent);
        errorRecord.setRequestParams(this.requestParams);
        errorRecord.setHttpMethod(this.httpMethod);
        errorRecord.setSessionId(this.sessionId);
        errorRecord.setTransactionId(this.transactionId);
        errorRecord.setErrorCode(this.errorCode);
        errorRecord.setIsHandled(this.isHandled);
        errorRecord.setHandledBy(this.handledBy);
        errorRecord.setResolution(this.resolution);
        errorRecord.setExtras(this.extras);
        return errorRecord;
    }
}
