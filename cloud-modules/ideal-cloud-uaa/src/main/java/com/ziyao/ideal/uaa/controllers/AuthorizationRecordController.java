package com.ziyao.ideal.uaa.controllers;

import com.ziyao.ideal.uaa.domain.dto.AuthorizationRecordDTO;
import com.ziyao.ideal.uaa.domain.entity.AuthorizationRecord;
import com.ziyao.ideal.uaa.service.AuthorizationRecordService;
import com.ziyao.ideal.web.base.PageQuery;
import com.ziyao.ideal.web.base.PagingHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 授权记录表 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/authorizationRecord")
@Tag(name = "授权记录表", description = "授权记录表")
public class AuthorizationRecordController {

    private final AuthorizationRecordService authorizationRecordService;

    /**
     * 保存
     */
    @PostMapping("/save")
    @Operation(summary = "保存授权记录表", description = "保存授权记录表")
    public void save(@RequestBody AuthorizationRecordDTO authorizationRecordDTO) {
        authorizationRecordService.save(authorizationRecordDTO);
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    @Operation(summary = "通过主键ID进行更新", description = "通过主键ID进行更新")
    public void updateById(@RequestBody AuthorizationRecordDTO authorizationRecordDTO) {
        // TODO 待完善
        authorizationRecordService.save(authorizationRecordDTO);
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
        authorizationRecordService.deleteById(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @Operation(summary = "分页查询数据", description = "分页查询数据")
    public Object list(@RequestBody PageQuery<AuthorizationRecordDTO> pageQuery) {
        // TODO 由于没有统一的分页处理插件，需要自行在控制层处理接受参数和分页信息
        Page<AuthorizationRecord> list = authorizationRecordService.page(pageQuery.getData(), PagingHelper.initPage(pageQuery));
        return new PagedModel<>(list);
    }
}