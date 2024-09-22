package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.dto.ApplicationDTO;
import com.ziyao.ideal.uaa.domain.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 应用系统 服务类
 * </p>
 *
 * @author ziyao
 */
public interface ApplicationService {

    /**
     * 保存 应用系统
     *
     * @param applicationDTO 待存储对象
     */
    void save(ApplicationDTO applicationDTO);

    /**
     * 通过主键修改 应用系统
     *
     * @param applicationDTO 待修改对象
     */
    void updateById(ApplicationDTO applicationDTO);

    /**
     * 通过主键删除 应用系统
     *
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 分页查询 应用系统
     *
     * @param applicationDTO 查询对象
     * @param pageable       分页对象
     * @return 返回分页对象
     */
    Page<Application> page(ApplicationDTO applicationDTO, Pageable pageable);
}
