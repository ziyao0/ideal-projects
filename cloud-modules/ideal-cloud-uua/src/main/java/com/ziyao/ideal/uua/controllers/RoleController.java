package com.ziyao.ideal.uua.controllers;

import com.ziyao.ideal.uua.domain.dto.RoleDTO;
import com.ziyao.ideal.uua.domain.entity.Role;
import com.ziyao.ideal.uua.service.RoleService;
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
 * 角色表 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController extends JpaBaseController<RoleService, Role, Integer> {

    private final RoleService roleService;

    @PostMapping("/save")
    public void save(@RequestBody RoleDTO entityDTO) {
        roleService.save(entityDTO.convert());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody RoleDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        roleService.save(entityDTO.convert());
    }

    /**
    * 通过id删除数据，有逻辑删除按照逻辑删除执行
    * <p>不支持联合主键</p>
    *
    * @param id 主键Id
    */
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable("id") Integer id) {
        roleService.deleteById(id);
    }
    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<RoleDTO> entityDTOList) {
        roleService.saveBatch(entityDTOList.stream().map(RoleDTO::convert).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Page<Role> list(PageParams<RoleDTO> pageParams) {
        return roleService.list(pageParams.getParams().convert(), Pages.initPage(pageParams));
    }
}
