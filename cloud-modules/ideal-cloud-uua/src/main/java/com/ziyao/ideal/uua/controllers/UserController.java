package com.ziyao.ideal.uua.controllers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.context.SecurityContextHolder;
import com.ziyao.ideal.uua.domain.dto.UserDTO;
import com.ziyao.ideal.uua.domain.entity.User;
import com.ziyao.ideal.uua.service.UserService;
import com.ziyao.ideal.web.base.BaseController;
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import com.ziyao.ideal.web.exception.Exceptions;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zhangziyao
 */
@RestController
@RequestMapping("/usercenter/user")
public class UserController extends BaseController<UserService, User> {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 保存用户信息
     */
    @PostMapping("/save")
    public void save(@RequestBody UserDTO entityDTO) {
        super.iService.save(entityDTO.of());
    }

    /**
     * 保存或更新
     */
    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody UserDTO entityDTO) {
        super.iService.saveOrUpdate(entityDTO.of());
    }

    @PostMapping("/updateById")
    public void updateById(@RequestBody UserDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw Exceptions.createIllegalArgumentException(null);
        }
        super.iService.updateById(entityDTO.of());
    }

    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<UserDTO> entityDTOList) {
        super.iService.saveBatch(entityDTOList.stream().map(UserDTO::of).collect(Collectors.toList()), 500);
    }

    /**
     * 条件分页查询
     *
     * @param pageParams 分页参数
     * @return 返回分页查询信息
     */
    @PostMapping("/page/get")
    public Page<User> getPage(@RequestBody PageParams<UserDTO> pageParams) {
        Page<User> page = Pages.initPage(pageParams, User.class);
        return userService.page(page, pageParams.getParams());
    }

    @GetMapping("/current")
    public Authentication userDetails() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
