package com.ziyao.ideal.usercenter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ziyao.ideal.usercenter.dto.ApplicationDTO;
import com.ziyao.ideal.usercenter.entity.Application;

/**
 * <p>
 * 应用系统 服务类
 * </p>
 *
 * @author zhangziyao
 */
public interface ApplicationService extends IService<Application> {

    /**
     * 分页查询
     */
    Page<Application> page(Page<Application> page, ApplicationDTO applicationDTO);
}
