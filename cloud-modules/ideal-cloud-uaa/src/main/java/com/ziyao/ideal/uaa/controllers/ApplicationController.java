package com.ziyao.ideal.uaa.controllers;

import com.ziyao.ideal.uaa.domain.dto.ApplicationDTO;
import com.ziyao.ideal.uaa.service.ApplicationService;
import com.ziyao.ideal.web.base.PageQuery;
import com.ziyao.ideal.web.base.PagingHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 应用系统 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
@Tag(name = "应用系统", description = "应用系统")
public class ApplicationController {

    private final ApplicationService applicationService;

    /**
     * 保存
     */
    @PostMapping("/save")
    @Operation(summary = "保存应用系统", description = "保存应用系统")
    public void save(@RequestBody ApplicationDTO applicationDTO) {
        applicationService.save(applicationDTO);
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    @Operation(summary = "通过主键ID进行更新", description = "通过主键ID进行更新")
    public void updateById(@RequestBody ApplicationDTO applicationDTO) {
        // TODO 待完善
        applicationService.save(applicationDTO);
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
        applicationService.deleteById(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "分页查询数据", description = "分页查询数据")
    public Object list(@RequestBody PageQuery<ApplicationDTO> pageQuery) {
        // TODO 由于没有统一的分页处理插件，需要自行在控制层处理接受参数和分页信息
        return applicationService.page(pageQuery.getData(), PagingHelper.initPage(pageQuery));
    }
}