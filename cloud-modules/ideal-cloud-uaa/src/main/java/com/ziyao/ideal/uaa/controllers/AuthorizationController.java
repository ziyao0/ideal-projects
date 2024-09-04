package com.ziyao.ideal.uaa.controllers;

import com.ziyao.ideal.uaa.domain.dto.AuthorizationDTO;
import com.ziyao.ideal.uaa.domain.entity.Authorization;
import com.ziyao.ideal.uaa.service.AuthorizationService;
import com.ziyao.ideal.jpa.extension.controllers.JpaBaseController;
import com.ziyao.ideal.web.base.PageQuery;
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
 *  前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/authorization")
public class AuthorizationController extends JpaBaseController<AuthorizationService, Authorization, Integer> {

    private final AuthorizationService authorizationService;

    @PostMapping("/save")
    public void save(@RequestBody AuthorizationDTO entityDTO) {
        authorizationService.save(entityDTO.convert());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody AuthorizationDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        authorizationService.save(entityDTO.convert());
    }

    /**
    * 通过id删除数据，有逻辑删除按照逻辑删除执行
    * <p>不支持联合主键</p>
    *
    * @param id 主键Id
    */
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable("id") Integer id) {
        authorizationService.deleteById(id);
    }
    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<AuthorizationDTO> entityDTOList) {
        authorizationService.saveBatch(entityDTOList.stream().map(AuthorizationDTO::convert).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Page<Authorization> list(PageQuery<AuthorizationDTO> pageQuery) {
        return authorizationService.list(pageQuery.getData().convert(), Pages.initPage(pageQuery));
    }
}
