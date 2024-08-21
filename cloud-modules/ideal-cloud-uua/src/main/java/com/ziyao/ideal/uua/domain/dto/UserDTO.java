package com.ziyao.ideal.uua.domain.dto;

import com.ziyao.ideal.uua.domain.convertor.UserConvertor;
import com.ziyao.ideal.uua.domain.entity.User;
import com.ziyao.ideal.web.orm.EntityDTO;
import lombok.Data;

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
public class UserDTO implements EntityDTO<User>, Serializable {

    @Serial
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

    public User convert() {
        return UserConvertor.INSTANCE.convert(this);
    }
}
