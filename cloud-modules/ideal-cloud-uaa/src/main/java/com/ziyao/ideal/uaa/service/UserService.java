package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.entity.User;
import com.ziyao.ideal.jpa.extension.service.JpaService;

/**
* <p>
    * 用户表 服务类
    * </p>
*
* @author ziyao
*/
public interface UserService extends JpaService<User,Integer> {

    boolean unlock(String username);

    boolean lock(String username);
}
