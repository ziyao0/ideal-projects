package com.ziyao.ideal.uua.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.web.orm.EntityDTO;
import com.ziyao.ideal.uua.domain.entity.User;
import com.ziyao.ideal.uua.domain.mapstruct.UserMapstruct;
import lombok.Data;
import java.util.Objects;
import com.ziyao.ideal.core.Strings;


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
public class UserDTO implements EntityDTO<User>, Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户凭证
     */
    private String password;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 姓名
     */
    private String idCardName;
    /**
     * 身份证号
     */
    private String idCardNo;
    /**
     * 性别（M: 男, F: 女, O: 其他）
     */
    private String gender;
    /**
     * 出生日期
     */
    private LocalDateTime dateOfBirth;
    /**
     * 地址
     */
    private String address;
    /**
     * 账号状态 1 正常 2 失效 3 禁用
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 删除状态 0正常 1 删除
     */
    private Integer deleted;
    /**
     * 最后登录时间
     */
    private LocalDateTime lastLogin;
    /**
     * 创建人id
     */
    private Integer createdBy;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 修改人id 
     */
    private Integer modifiedBy;
    /**
     * 修改时间
     */
    private LocalDateTime modifiedAt;

    /**
     * 组装查询条件，可根据具体情况做出修改
     *
     * @see LambdaQueryWrapper
     */
    public LambdaQueryWrapper<User> initWrapper() {

        return Wrappers.lambdaQuery(User.class)
                // 用户账号
                .likeRight(Strings.hasLength(username), User::getUsername, username)
                // 昵称
                .likeRight(Strings.hasLength(nickname), User::getNickname, nickname)
                // 用户凭证
                .likeRight(Strings.hasLength(password), User::getPassword, password)
                // 手机号
                .likeRight(Strings.hasLength(mobile), User::getMobile, mobile)
                // 姓名
                .likeRight(Strings.hasLength(idCardName), User::getIdCardName, idCardName)
                // 身份证号
                .likeRight(Strings.hasLength(idCardNo), User::getIdCardNo, idCardNo)
                // 性别（M: 男, F: 女, O: 其他）
                .likeRight(Strings.hasLength(gender), User::getGender, gender)
                // 出生日期
                .eq(Objects.nonNull(dateOfBirth), User::getDateOfBirth, dateOfBirth)
                // 地址
                .likeRight(Strings.hasLength(address), User::getAddress, address)
                // 账号状态 1 正常 2 失效 3 禁用
                .eq(Objects.nonNull(status), User::getStatus, status)
                // 排序
                .eq(Objects.nonNull(sort), User::getSort, sort)
                // 删除状态 0正常 1 删除
                .eq(Objects.nonNull(deleted), User::getDeleted, deleted)
                // 最后登录时间
                .eq(Objects.nonNull(lastLogin), User::getLastLogin, lastLogin)
                // 创建人id
                .eq(Objects.nonNull(createdBy), User::getCreatedBy, createdBy)
                // 创建时间
                .eq(Objects.nonNull(createdAt), User::getCreatedAt, createdAt)
                // 修改人id 
                .eq(Objects.nonNull(modifiedBy), User::getModifiedBy, modifiedBy)
                // 修改时间
                .eq(Objects.nonNull(modifiedAt), User::getModifiedAt, modifiedAt)
                // 排序
                .orderByAsc(User::getSort)
                ;
    }

    public User of() {
        return UserMapstruct.INSTANCE.of(this);
    }
}
