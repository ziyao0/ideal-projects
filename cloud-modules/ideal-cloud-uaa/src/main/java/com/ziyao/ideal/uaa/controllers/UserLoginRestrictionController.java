package com.ziyao.ideal.uaa.controllers;

import com.ziyao.ideal.uaa.domain.dto.UserLoginRestrictionDTO;
import com.ziyao.ideal.uaa.service.UserLoginRestrictionService;
import com.ziyao.ideal.web.base.PageQuery;
import com.ziyao.ideal.web.base.PagingHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户登录限制表 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/userLoginRestriction")
@Tag(name = "用户登录限制表", description = "用户登录限制表")
public class UserLoginRestrictionController {

    private final UserLoginRestrictionService userLoginRestrictionService;

    /**
     * 保存
     */
    @PostMapping("/save")
    @Operation(summary = "保存用户登录限制表", description = "保存用户登录限制表")
    public void save(@RequestBody UserLoginRestrictionDTO userLoginRestrictionDTO) {
        userLoginRestrictionService.save(userLoginRestrictionDTO);
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    @Operation(summary = "通过主键ID进行更新", description = "通过主键ID进行更新")
    public void updateById(@RequestBody UserLoginRestrictionDTO userLoginRestrictionDTO) {
        // TODO 待完善
        userLoginRestrictionService.save(userLoginRestrictionDTO);
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
        userLoginRestrictionService.deleteById(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "分页查询数据", description = "分页查询数据")
    public Object list(@RequestBody PageQuery<UserLoginRestrictionDTO> pageQuery) {
        // TODO 由于没有统一的分页处理插件，需要自行在控制层处理接受参数和分页信息
        return userLoginRestrictionService.page(pageQuery.getData(), PagingHelper.initPage(pageQuery));
    }
}