package com.ziyao.ideal.uua.controllers;

import com.ziyao.ideal.jpa.extension.controllers.JpaBaseController;
import com.ziyao.ideal.uua.domain.dto.AuthorizationRecordDTO;
import com.ziyao.ideal.uua.domain.entity.AuthorizationRecord;
import com.ziyao.ideal.uua.service.AuthorizationRecordService;
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import com.ziyao.ideal.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
public class AuthorizationRecordController extends JpaBaseController<AuthorizationRecordService, AuthorizationRecord, Integer> {

    private final AuthorizationRecordService authorizationRecordService;

    @PostMapping("/save")
    public void save(@RequestBody AuthorizationRecordDTO entityDTO) {
        authorizationRecordService.save(entityDTO.convert());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody AuthorizationRecordDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        authorizationRecordService.save(entityDTO.convert());
    }

    /**
     * 通过id删除数据，有逻辑删除按照逻辑删除执行
     * <p>不支持联合主键</p>
     *
     * @param id 主键Id
     */
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable("id") Integer id) {
        authorizationRecordService.deleteById(id);
    }

    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<AuthorizationRecordDTO> entityDTOList) {
        authorizationRecordService.saveBatch(entityDTOList.stream().map(AuthorizationRecordDTO::convert).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Page<AuthorizationRecord> list(PageParams<AuthorizationRecordDTO> pageParams) {
        return authorizationRecordService.list(pageParams.getParams().convert(), Pages.initPage(pageParams));
    }
}
