package com.ziyao.ideal.uua.controllers;

import com.ziyao.ideal.jpa.extension.controllers.JpaBaseController;
import com.ziyao.ideal.uua.domain.dto.UserLoginRestrictionDTO;
import com.ziyao.ideal.uua.domain.entity.UserLoginRestriction;
import com.ziyao.ideal.uua.service.UserLoginRestrictionService;
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
 * 用户登录限制表 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/userLoginRestriction")
public class UserLoginRestrictionController extends JpaBaseController<UserLoginRestrictionService, UserLoginRestriction, Integer> {

    private final UserLoginRestrictionService userLoginRestrictionService;

    @PostMapping("/save")
    public void save(@RequestBody UserLoginRestrictionDTO entityDTO) {
        userLoginRestrictionService.save(entityDTO.convert());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody UserLoginRestrictionDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        userLoginRestrictionService.save(entityDTO.convert());
    }

    /**
     * 通过id删除数据，有逻辑删除按照逻辑删除执行
     * <p>不支持联合主键</p>
     *
     * @param id 主键Id
     */
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable("id") Integer id) {
        userLoginRestrictionService.deleteById(id);
    }

    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<UserLoginRestrictionDTO> entityDTOList) {
        userLoginRestrictionService.saveBatch(entityDTOList.stream().map(UserLoginRestrictionDTO::convert).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Page<UserLoginRestriction> list(PageParams<UserLoginRestrictionDTO> pageParams) {
        return userLoginRestrictionService.list(pageParams.getParams().convert(), Pages.initPage(pageParams));
    }
}
