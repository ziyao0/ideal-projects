package com.ziyao.ideal.uua.controllers;

import com.ziyao.ideal.uua.domain.dto.AuthorizationDTO;
import com.ziyao.ideal.uua.domain.entity.Authorization;
import com.ziyao.ideal.uua.service.AuthorizationService;
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
    authorizationService.save(entityDTO.of());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody AuthorizationDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        authorizationService.save(entityDTO.of());
    }

    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<AuthorizationDTO> entityDTOList) {
        authorizationService.saveBatch(entityDTOList.stream().map(AuthorizationDTO::of).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/searchSimilar")
    public Page<Authorization> searchSimilar(PageParams<AuthorizationDTO> pageParams) {
        return authorizationService.searchSimilar(pageParams.getParams().of(), Pages.initPage(pageParams));
    }
}
