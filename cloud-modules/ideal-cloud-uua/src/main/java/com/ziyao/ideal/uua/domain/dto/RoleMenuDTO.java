package com.ziyao.ideal.uua.domain.dto;

import com.ziyao.ideal.uua.domain.convertor.RoleMenuConvertor;
import com.ziyao.ideal.uua.domain.entity.RoleMenu;
import com.ziyao.ideal.web.orm.EntityDTO;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author ziyao
 */
@Data
public class RoleMenuDTO implements EntityDTO<RoleMenu>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 系统id
     */
    private Integer appId;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 菜单id
     */
    private Integer menuId;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    public RoleMenu convert() {
        return RoleMenuConvertor.INSTANCE.convert(this);
    }
}