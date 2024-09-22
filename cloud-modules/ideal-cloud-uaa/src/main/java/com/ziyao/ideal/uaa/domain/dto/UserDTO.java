package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ziyao
 */
@Data
@Schema(description = "用户表")
public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Integer id;

    /**
     * 用户账号
     */
    @Schema(description = "用户账号")
    private String username;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 用户凭证
     */
    @Schema(description = "用户凭证")
    private String password;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String mobile;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String idCardName;

    /**
     * 身份证号
     */
    @Schema(description = "身份证号")
    private String idCardNo;

    /**
     * 性别（M: 男, F: 女, O: 其他）
     */
    @Schema(description = "性别（M: 男, F: 女, O: 其他）")
    private String gender;

    /**
     * 出生日期
     */
    @Schema(description = "出生日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfBirth;
    /**
     * start time for 出生日期
     */
    @Schema(description = "出生日期-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDateOfBirth;

    /**
     * end time for 出生日期
     */
    @Schema(description = "出生日期-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDateOfBirth;

    /**
     * 地址
     */
    @Schema(description = "地址")
    private String address;

    /**
     * 账号状态 1 正常 2 失效 3 禁用
     */
    @Schema(description = "账号状态 1 正常 2 失效 3 禁用")
    private Integer status;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Short sort;

    /**
     * 删除状态 0正常 1 删除
     */
    @Schema(description = "删除状态 0正常 1 删除")
    private Integer deleted;

    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLogin;
    /**
     * start time for 最后登录时间
     */
    @Schema(description = "最后登录时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startLastLogin;

    /**
     * end time for 最后登录时间
     */
    @Schema(description = "最后登录时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endLastLogin;

    /**
     * 创建人id
     */
    @Schema(description = "创建人id")
    private Integer createdBy;

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
     * 修改人id
     */
    @Schema(description = "修改人id ")
    private Integer modifiedBy;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
    /**
     * start time for 修改时间
     */
    @Schema(description = "修改时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startModifiedAt;

    /**
     * end time for 修改时间
     */
    @Schema(description = "修改时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endModifiedAt;

    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setNickname(this.nickname);
        user.setPassword(this.password);
        user.setMobile(this.mobile);
        user.setIdCardName(this.idCardName);
        user.setIdCardNo(this.idCardNo);
        user.setGender(this.gender);
        user.setDateOfBirth(this.dateOfBirth);
        user.setAddress(this.address);
        user.setStatus(this.status);
        user.setSort(this.sort);
        user.setDeleted(this.deleted);
        user.setLastLogin(this.lastLogin);
        user.setCreatedBy(this.createdBy);
        user.setCreatedAt(this.createdAt);
        user.setModifiedBy(this.modifiedBy);
        user.setModifiedAt(this.modifiedAt);
        return user;
    }
}
