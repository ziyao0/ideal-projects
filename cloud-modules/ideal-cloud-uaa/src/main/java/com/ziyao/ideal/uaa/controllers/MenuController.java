package com.ziyao.ideal.uaa.controllers;

import com.ziyao.ideal.uaa.domain.dto.MenuDTO;
import com.ziyao.ideal.uaa.service.MenuService;
import com.ziyao.ideal.web.base.PageQuery;
import com.ziyao.ideal.web.base.PagingHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 菜单资源表 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
@Tag(name = "菜单资源表", description = "菜单资源表")
public class MenuController {

    private final MenuService menuService;

    /**
     * 保存
     */
    @PostMapping("/save")
    @Operation(summary = "保存菜单资源表", description = "保存菜单资源表")
    public void save(@RequestBody MenuDTO menuDTO) {
        menuService.save(menuDTO);
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    @Operation(summary = "通过主键ID进行更新", description = "通过主键ID进行更新")
    public void updateById(@RequestBody MenuDTO menuDTO) {
        // TODO 待完善
        menuService.save(menuDTO);
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
        menuService.deleteById(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "分页查询数据", description = "分页查询数据")
    public Object list(@RequestBody PageQuery<MenuDTO> pageQuery) {
        // TODO 由于没有统一的分页处理插件，需要自行在控制层处理接受参数和分页信息
        return menuService.page(pageQuery.getData(), PagingHelper.initPage(pageQuery));
    }
}