package com.ziyao.ideal.uaa.controllers;

import com.ziyao.ideal.uaa.domain.dto.RoleMenuDTO;
import com.ziyao.ideal.uaa.service.RoleMenuService;
import com.ziyao.ideal.web.base.PageQuery;
import com.ziyao.ideal.web.base.PagingHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
@Tag(name = "角色菜单表", description = "角色菜单表")
public class RoleMenuController {

    private final RoleMenuService roleMenuService;

    /**
     * 保存
     */
    @PostMapping("/save")
    @Operation(summary = "保存角色菜单表", description = "保存角色菜单表")
    public void save(@RequestBody RoleMenuDTO roleMenuDTO) {
        roleMenuService.save(roleMenuDTO);
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    @Operation(summary = "通过主键ID进行更新", description = "通过主键ID进行更新")
    public void updateById(@RequestBody RoleMenuDTO roleMenuDTO) {
        // TODO 待完善
        roleMenuService.save(roleMenuDTO);
    }

    /**
     * 通过id删除数据，有逻辑删除按照逻辑删除执行
     * <p>不支持联合主键</p>
     *
     * @param id 主键Id
     */
    @GetMapping("/remove/{id}")
    @Operation(summary = "通过主键进行删除", description = "通过主键进行删除")
    public void removeById(@PathVariable("id") Integer id) {
        roleMenuService.deleteById(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "分页查询数据", description = "分页查询数据")
    public Object list(@RequestBody PageQuery<RoleMenuDTO> pageQuery) {
        // TODO 由于没有统一的分页处理插件，需要自行在控制层处理接受参数和分页信息
        return roleMenuService.page(pageQuery.getData(), PagingHelper.initPage(pageQuery));
    }
}