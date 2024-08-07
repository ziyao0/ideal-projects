package com.ziyao.ideal.uua.controllers;

import com.ziyao.ideal.uua.domain.dto.AuthorizationRecordDTO;
import com.ziyao.ideal.uua.domain.entity.AuthorizationRecord;
import com.ziyao.ideal.uua.service.AuthorizationRecordService;
import com.ziyao.ideal.web.base.JpaBaseController;
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import com.ziyao.ideal.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        authorizationRecordService.save(entityDTO.of());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody AuthorizationRecordDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        authorizationRecordService.save(entityDTO.of());
    }

    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<AuthorizationRecordDTO> entityDTOList) {
        authorizationRecordService.saveBatch(entityDTOList.stream().map(AuthorizationRecordDTO::of).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/searchSimilar")
    public Page<AuthorizationRecord> searchSimilar(PageParams<AuthorizationRecordDTO> pageParams) {
        return authorizationRecordService.searchSimilar(pageParams.getParams().of(), Pages.initPage(pageParams));
    }
}
