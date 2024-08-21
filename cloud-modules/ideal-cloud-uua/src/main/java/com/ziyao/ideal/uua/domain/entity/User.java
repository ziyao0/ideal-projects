package com.ziyao.ideal.uua.domain.entity;

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
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * id:用户id
     */
    @Id
    private Integer id;

    /**
     * username:用户账号
     */
    private String username;

    /**
     * nickname:昵称
     */
    private String nickname;

    /**
     * password:用户凭证
     */
    private String password;

    /**
     * mobile:手机号
     */
    private String mobile;

    /**
     * idCardName:姓名
     */
    private String idCardName;

    /**
     * idCardNo:身份证号
     */
    private String idCardNo;

    /**
     * gender:性别（M: 男, F: 女, O: 其他）
     */
    private String gender;

    /**
     * dateOfBirth:出生日期
     */
    private LocalDateTime dateOfBirth;

    /**
     * address:地址
     */
    private String address;

    /**
     * status:账号状态 1 正常 2 失效 3 禁用
     */
    private Integer status;

    /**
     * sort:排序
     */
    private Integer sort;

    /**
     * deleted:删除状态 0正常 1 删除
     */
    private Integer deleted;

    /**
     * lastLogin:最后登录时间
     */
    private LocalDateTime lastLogin;

    /**
     * createdBy:创建人id
     */
    private Integer createdBy;

    /**
     * createdAt:创建时间
     */
    private LocalDateTime createdAt;

    /**
     * modifiedBy:修改人id
     */
    private Integer modifiedBy;

    /**
     * modifiedAt:修改时间
     */
    private LocalDateTime modifiedAt;
}
