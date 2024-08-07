package com.ziyao.ideal.uua.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.web.orm.EntityDTO;
import com.ziyao.ideal.uua.domain.entity.RoleMenu;
import com.ziyao.ideal.uua.domain.mapstruct.RoleMenuMapstruct;
import lombok.Data;
import java.util.Objects;
import com.ziyao.ideal.core.Strings;

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

    /**
     * 组装查询条件，可根据具体情况做出修改
     *
     * @see LambdaQueryWrapper
     */
    public LambdaQueryWrapper<RoleMenu> initWrapper() {

        return Wrappers.lambdaQuery(RoleMenu.class)
                // 创建时间
                .eq(Objects.nonNull(createdAt), RoleMenu::getCreatedAt, createdAt)
                ;
    }

    public RoleMenu of() {
        return RoleMenuMapstruct.INSTANCE.of(this);
    }
}
