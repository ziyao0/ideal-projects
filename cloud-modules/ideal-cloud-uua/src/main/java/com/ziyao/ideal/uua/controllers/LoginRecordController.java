package com.ziyao.ideal.uua.controllers;

import com.ziyao.ideal.uua.domain.dto.LoginRecordDTO;
import com.ziyao.ideal.uua.domain.entity.LoginRecord;
import com.ziyao.ideal.uua.service.LoginRecordService;
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
 * 登录记录表 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loginRecord")
public class LoginRecordController extends JpaBaseController<LoginRecordService, LoginRecord, Integer> {

    private final LoginRecordService loginRecordService;

    @PostMapping("/save")
    public void save(@RequestBody LoginRecordDTO entityDTO) {
        loginRecordService.save(entityDTO.convert());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody LoginRecordDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        loginRecordService.save(entityDTO.convert());
    }

    /**
    * 通过id删除数据，有逻辑删除按照逻辑删除执行
    * <p>不支持联合主键</p>
    *
    * @param id 主键Id
    */
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable("id") Integer id) {
        loginRecordService.deleteById(id);
    }
    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<LoginRecordDTO> entityDTOList) {
        loginRecordService.saveBatch(entityDTOList.stream().map(LoginRecordDTO::convert).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Page<LoginRecord> list(PageParams<LoginRecordDTO> pageParams) {
        return loginRecordService.list(pageParams.getParams().convert(), Pages.initPage(pageParams));
    }
}
