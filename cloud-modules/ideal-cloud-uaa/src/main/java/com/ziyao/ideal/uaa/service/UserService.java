package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.entity.User;
import com.ziyao.ideal.jpa.extension.service.JapService;

/**
* <p>
    * 用户表 服务类
    * </p>
*
* @author ziyao
*/
public interface UserService extends JapService<User,Integer> {

    boolean unlock(String username);

    boolean lock(String username);
}
