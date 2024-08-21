package com.ziyao.ideal.uua.service;

import com.ziyao.ideal.uua.domain.entity.LoginConfig;
import com.ziyao.ideal.jpa.extension.service.JapService;

/**
* <p>
    * 登录配置表 服务类
    * </p>
*
* @author ziyao
*/
public interface LoginConfigService extends JapService<LoginConfig,Long> {

    LoginConfig getAccountPasswordLoginConfig();
}
