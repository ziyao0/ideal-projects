package com.ziyao.ideal.config.controllers;

import com.ziyao.ideal.config.domain.dto.ConfigPropertyDTO;
import com.ziyao.ideal.config.domain.entity.ConfigProperty;
import com.ziyao.ideal.config.service.ConfigPropertyService;
import com.ziyao.ideal.web.base.PageQuery;
import com.ziyao.ideal.web.base.Pages;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 配置属性 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/config/property")
@Tag(name = "配置属性", description = "配置属性")
public class ConfigPropertyController {

    private final ConfigPropertyService configPropertyService;

    /**
     * 保存
     */
    @PostMapping("/save")
    @Operation(summary = "保存配置属性", description = "保存配置属性")
    public void save(@RequestBody ConfigPropertyDTO configPropertyDTO) {
        configPropertyService.save(configPropertyDTO.toEntity());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    @Operation(summary = "通过主键ID进行更新", description = "通过主键ID进行更新")
    public void updateById(@RequestBody ConfigPropertyDTO configPropertyDTO) {
        // TODO 待完善
        configPropertyService.save(configPropertyDTO.toEntity());
    }

    /**
     * 通过id删除数据，有逻辑删除按照逻辑删除执行
     * <p>不支持联合主键</p>
     *
     * @param id 主键Id
     */
    @DeleteMapping("/remove/{id}")
    @Operation(summary = "通过主键进行删除", description = "通过主键进行删除")
    public void removeById(@PathVariable("id") Integer id) {
        configPropertyService.deleteById(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "分页查询数据", description = "分页查询数据")
    public Page<ConfigProperty> list(@RequestBody PageQuery<ConfigPropertyDTO> pageQuery) {
        // 由于没有统一的分页处理插件，需要自行在控制层处理接受参数和分页信息
        return configPropertyService.list(pageQuery.getData().toEntity(), Pages.initPage(pageQuery));
    }
}