package com.ziyao.ideal.uaa.controllers;

import com.ziyao.ideal.uaa.domain.dto.LoginConfigDTO;
import com.ziyao.ideal.uaa.domain.entity.LoginConfig;
import com.ziyao.ideal.uaa.service.LoginConfigService;
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
 * 登录配置表 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loginConfig")
public class LoginConfigController extends JpaBaseController<LoginConfigService, LoginConfig, Long> {

    private final LoginConfigService loginConfigService;

    @PostMapping("/save")
    public void save(@RequestBody LoginConfigDTO entityDTO) {
        loginConfigService.save(entityDTO.convert());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody LoginConfigDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        loginConfigService.save(entityDTO.convert());
    }

    /**
    * 通过id删除数据，有逻辑删除按照逻辑删除执行
    * <p>不支持联合主键</p>
    *
    * @param id 主键Id
    */
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable("id") Long id) {
        loginConfigService.deleteById(id);
    }
    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<LoginConfigDTO> entityDTOList) {
        loginConfigService.saveBatch(entityDTOList.stream().map(LoginConfigDTO::convert).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Page<LoginConfig> list(PageParams<LoginConfigDTO> pageParams) {
        return loginConfigService.list(pageParams.getParams().convert(), Pages.initPage(pageParams));
    }
}
