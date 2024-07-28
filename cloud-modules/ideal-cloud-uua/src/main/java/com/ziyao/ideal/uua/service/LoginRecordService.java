package com.ziyao.ideal.uua.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ziyao.ideal.uua.domain.dto.LoginRecordDTO;
import com.ziyao.ideal.uua.domain.entity.LoginRecord;

/**
 * <p>
 * 登录记录表 服务类
 * </p>
 *
 * @author zhangziyao
 */
public interface LoginRecordService extends IService<LoginRecord> {

    /**
     * 分页查询
     */
    Page<LoginRecord> page(Page<LoginRecord> page, LoginRecordDTO loginRecordDTO);
}
