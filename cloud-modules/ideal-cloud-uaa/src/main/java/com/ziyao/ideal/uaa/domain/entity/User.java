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
 * 用户表
 * </p>
 *
 * @author ziyao
 */
@Getter
@Setter
@Builder
@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户表")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id:用户id
     */
    @Id
    @Schema(description = "用户id")
    private Integer id;

    /**
     * username:用户账号
     */
    @Schema(description = "用户账号")
    private String username;

    /**
     * nickname:昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * password:用户凭证
     */
    @Schema(description = "用户凭证")
    private String password;

    /**
     * mobile:手机号
     */
    @Schema(description = "手机号")
    private String mobile;

    /**
     * idCardName:姓名
     */
    @Schema(description = "姓名")
    private String idCardName;

    /**
     * idCardNo:身份证号
     */
    @Schema(description = "身份证号")
    private String idCardNo;

    /**
     * gender:性别（M: 男, F: 女, O: 其他）
     */
    @Schema(description = "性别（M: 男, F: 女, O: 其他）")
    private String gender;

    /**
     * dateOfBirth:出生日期
     */
    @Schema(description = "出生日期")
    private LocalDateTime dateOfBirth;

    /**
     * address:地址
     */
    @Schema(description = "地址")
    private String address;

    /**
     * status:账号状态 1 正常 2 失效 3 禁用
     */
    @Schema(description = "账号状态 1 正常 2 失效 3 禁用")
    private Integer status;

    /**
     * sort:排序
     */
    @Schema(description = "排序")
    private Short sort;

    /**
     * deleted:删除状态 0正常 1 删除
     */
    @Schema(description = "删除状态 0正常 1 删除")
    private Integer deleted;

    /**
     * lastLogin:最后登录时间
     */
    @Schema(description = "最后登录时间")
    private LocalDateTime lastLogin;

    /**
     * createdBy:创建人id
     */
    @Schema(description = "创建人id")
    private Integer createdBy;

    /**
     * createdAt:创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    /**
     * modifiedBy:修改人id
     */
    @Schema(description = "修改人id ")
    private Integer modifiedBy;

    /**
     * modifiedAt:修改时间
     */
    @Schema(description = "修改时间")
    private LocalDateTime modifiedAt;


    public static class Builder {

        private final User user = new User();

        public Builder id(Integer id) {
            this.user.id = id;
            return this;
        }

        public Builder username(String username) {
            this.user.username = username;
            return this;
        }

        public Builder nickname(String nickname) {
            this.user.nickname = nickname;
            return this;
        }

        public Builder password(String password) {
            this.user.password = password;
            return this;
        }

        public Builder mobile(String mobile) {
            this.user.mobile = mobile;
            return this;
        }

        public Builder idCardName(String idCardName) {
            this.user.idCardName = idCardName;
            return this;
        }

        public Builder idCardNo(String idCardNo) {
            this.user.idCardNo = idCardNo;
            return this;
        }

        public Builder gender(String gender) {
            this.user.gender = gender;
            return this;
        }

        public Builder dateOfBirth(LocalDateTime dateOfBirth) {
            this.user.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder address(String address) {
            this.user.address = address;
            return this;
        }

        public Builder status(Integer status) {
            this.user.status = status;
            return this;
        }

        public Builder sort(Short sort) {
            this.user.sort = sort;
            return this;
        }

        public Builder deleted(Integer deleted) {
            this.user.deleted = deleted;
            return this;
        }

        public Builder lastLogin(LocalDateTime lastLogin) {
            this.user.lastLogin = lastLogin;
            return this;
        }

        public Builder createdBy(Integer createdBy) {
            this.user.createdBy = createdBy;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.user.createdAt = createdAt;
            return this;
        }

        public Builder modifiedBy(Integer modifiedBy) {
            this.user.modifiedBy = modifiedBy;
            return this;
        }

        public Builder modifiedAt(LocalDateTime modifiedAt) {
            this.user.modifiedAt = modifiedAt;
            return this;
        }


        public User build() {
            return this.user;
        }
    }
}
