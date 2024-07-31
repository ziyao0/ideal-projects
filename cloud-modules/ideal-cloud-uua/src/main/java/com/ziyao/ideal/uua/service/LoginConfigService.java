package com.ziyao.ideal.uua.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ziyao.ideal.uua.domain.dto.LoginConfigDTO;
import com.ziyao.ideal.uua.domain.entity.LoginConfig;

/**
 * <p>
 * 登录配置表 服务类
 * </p>
 *
 * @author ziyao
 */
public interface LoginConfigService extends IService<LoginConfig> {

    /**
     * 分页查询
     */
    Page<LoginConfig> page(Page<LoginConfig> page, LoginConfigDTO loginConfigDTO);


    LoginConfig getAccountPasswordLoginConfig();
}
