package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.dto.LoginConfigDTO;
import com.ziyao.ideal.uaa.domain.entity.LoginConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 登录配置表 服务类
 * </p>
 *
 * @author ziyao
 */
public interface LoginConfigService {

    /**
     * 保存 登录配置表
     *
     * @param loginConfigDTO 待存储对象
     */
    void save(LoginConfigDTO loginConfigDTO);

    /**
     * 通过主键修改 登录配置表
     *
     * @param loginConfigDTO 待修改对象
     */
    void updateById(LoginConfigDTO loginConfigDTO);

    /**
     * 通过主键删除 登录配置表
     *
     * @param id 主键id
     */
    void deleteById(Long id);

    /**
     * 分页查询 登录配置表
     *
     * @param loginConfigDTO 查询对象
     * @param pageable       分页对象
     * @return 返回分页对象
     */
    Page<LoginConfig> page(LoginConfigDTO loginConfigDTO, Pageable pageable);

    LoginConfig getAccountPasswordLoginConfig();
}
