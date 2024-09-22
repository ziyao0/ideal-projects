package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.dto.GlobalLoginRestrictionDTO;
import com.ziyao.ideal.uaa.domain.entity.GlobalLoginRestriction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 全局登录限制表 服务类
 * </p>
 *
 * @author ziyao
 */
public interface GlobalLoginRestrictionService {

    /**
     * 保存 全局登录限制表
     *
     * @param globalLoginRestrictionDTO 待存储对象
     */
    void save(GlobalLoginRestrictionDTO globalLoginRestrictionDTO);

    /**
     * 通过主键修改 全局登录限制表
     *
     * @param globalLoginRestrictionDTO 待修改对象
     */
    void updateById(GlobalLoginRestrictionDTO globalLoginRestrictionDTO);

    /**
     * 通过主键删除 全局登录限制表
     *
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 分页查询 全局登录限制表
     *
     * @param globalLoginRestrictionDTO 查询对象
     * @param pageable                  分页对象
     * @return 返回分页对象
     */
    Page<GlobalLoginRestriction> page(GlobalLoginRestrictionDTO globalLoginRestrictionDTO, Pageable pageable);
}
