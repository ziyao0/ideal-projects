package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.entity.LoginConfig;
import com.ziyao.ideal.jpa.extension.service.JpaService;

/**
* <p>
    * 登录配置表 服务类
    * </p>
*
* @author ziyao
*/
public interface LoginConfigService extends JpaService<LoginConfig,Long> {

    LoginConfig getAccountPasswordLoginConfig();
}
