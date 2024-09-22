package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.dto.AuthorizationDTO;
import com.ziyao.ideal.uaa.domain.entity.Authorization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ziyao
 */
public interface AuthorizationService {

    /**
     * 保存
     *
     * @param authorizationDTO 待存储对象
     */
    void save(AuthorizationDTO authorizationDTO);

    /**
     * 通过主键修改
     *
     * @param authorizationDTO 待修改对象
     */
    void updateById(AuthorizationDTO authorizationDTO);

    /**
     * 通过主键删除
     *
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 分页查询
     *
     * @param authorizationDTO 查询对象
     * @param pageable         分页对象
     * @return 返回分页对象
     */
    Page<Authorization> page(AuthorizationDTO authorizationDTO, Pageable pageable);
}
