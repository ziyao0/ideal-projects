package com.ziyao.ideal.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziyao.ideal.usercenter.domain.dto.RoleMenuDTO;
import com.ziyao.ideal.usercenter.domain.entity.RoleMenu;
import com.ziyao.ideal.usercenter.repository.mapper.RoleMenuMapper;
import com.ziyao.ideal.usercenter.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author zhangziyao
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Page<RoleMenu> page(Page<RoleMenu> page, RoleMenuDTO roleMenuDTO) {
        LambdaQueryWrapper<RoleMenu> wrapper = roleMenuDTO.initWrapper();
        // to do 2023/5/6 默认排序字段 sort/sorted(默认是为ASC)值越小、越往前
        return roleMenuMapper.selectPage(page, wrapper);
    }
}
