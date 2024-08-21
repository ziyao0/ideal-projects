package com.ziyao.ideal.uua.controllers;

import com.ziyao.ideal.uua.domain.dto.RoleMenuDTO;
import com.ziyao.ideal.uua.domain.entity.RoleMenu;
import com.ziyao.ideal.uua.service.RoleMenuService;
import com.ziyao.ideal.jpa.extension.controllers.JpaBaseController;
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import com.ziyao.ideal.web.exception.ServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * 角色菜单表 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/roleMenu")
public class RoleMenuController extends JpaBaseController<RoleMenuService, RoleMenu, Integer> {

    private final RoleMenuService roleMenuService;

    @PostMapping("/save")
    public void save(@RequestBody RoleMenuDTO entityDTO) {
        roleMenuService.save(entityDTO.convert());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody RoleMenuDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        roleMenuService.save(entityDTO.convert());
    }

    /**
    * 通过id删除数据，有逻辑删除按照逻辑删除执行
    * <p>不支持联合主键</p>
    *
    * @param id 主键Id
    */
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable("id") Integer id) {
        roleMenuService.deleteById(id);
    }
    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<RoleMenuDTO> entityDTOList) {
        roleMenuService.saveBatch(entityDTOList.stream().map(RoleMenuDTO::convert).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Page<RoleMenu> list(PageParams<RoleMenuDTO> pageParams) {
        return roleMenuService.list(pageParams.getParams().convert(), Pages.initPage(pageParams));
    }
}