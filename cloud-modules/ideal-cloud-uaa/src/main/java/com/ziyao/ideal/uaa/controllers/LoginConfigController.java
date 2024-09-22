package com.ziyao.ideal.uaa.controllers;

import com.ziyao.ideal.uaa.domain.dto.LoginConfigDTO;
import com.ziyao.ideal.uaa.service.LoginConfigService;
import com.ziyao.ideal.web.base.PageQuery;
import com.ziyao.ideal.web.base.PagingHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 登录配置表 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loginConfig")
@Tag(name = "登录配置表", description = "登录配置表")
public class LoginConfigController {

    private final LoginConfigService loginConfigService;

    /**
     * 保存
     */
    @PostMapping("/save")
    @Operation(summary = "保存登录配置表", description = "保存登录配置表")
    public void save(@RequestBody LoginConfigDTO loginConfigDTO) {
        loginConfigService.save(loginConfigDTO);
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    @Operation(summary = "通过主键ID进行更新", description = "通过主键ID进行更新")
    public void updateById(@RequestBody LoginConfigDTO loginConfigDTO) {
        // TODO 待完善
        loginConfigService.save(loginConfigDTO);
    }

    /**
     * 通过id删除数据，有逻辑删除按照逻辑删除执行
     * <p>不支持联合主键</p>
     *
     * @param id 主键Id
     */
    @GetMapping("/remove/{id}")
    @Operation(summary = "通过主键进行删除", description = "通过主键进行删除")
    public void removeById(@PathVariable("id") Long id) {
        loginConfigService.deleteById(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "分页查询数据", description = "分页查询数据")
    public Object list(@RequestBody PageQuery<LoginConfigDTO> pageQuery) {
        // TODO 由于没有统一的分页处理插件，需要自行在控制层处理接受参数和分页信息
        return loginConfigService.page(pageQuery.getData(), PagingHelper.initPage(pageQuery));
    }
}