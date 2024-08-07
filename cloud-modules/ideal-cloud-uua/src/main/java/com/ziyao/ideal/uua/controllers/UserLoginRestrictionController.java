package com.ziyao.ideal.uua.controllers;

import com.ziyao.ideal.uua.domain.dto.UserLoginRestrictionDTO;
import com.ziyao.ideal.uua.domain.entity.UserLoginRestriction;
import com.ziyao.ideal.uua.service.UserLoginRestrictionService;
import com.ziyao.ideal.web.base.JpaBaseController;
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import com.ziyao.ideal.web.exception.ServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

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
    userLoginRestrictionService.save(entityDTO.of());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody UserLoginRestrictionDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        userLoginRestrictionService.save(entityDTO.of());
    }

    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<UserLoginRestrictionDTO> entityDTOList) {
        userLoginRestrictionService.saveBatch(entityDTOList.stream().map(UserLoginRestrictionDTO::of).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/searchSimilar")
    public Page<UserLoginRestriction> searchSimilar(PageParams<UserLoginRestrictionDTO> pageParams) {
        return userLoginRestrictionService.searchSimilar(pageParams.getParams().of(), Pages.initPage(pageParams));
    }
}
