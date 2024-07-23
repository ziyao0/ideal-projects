package com.ziyao.harbor.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziyao.harbor.usercenter.dto.LoginRecordDTO;
import com.ziyao.harbor.usercenter.entity.LoginRecord;
import com.ziyao.harbor.usercenter.repository.mapper.LoginRecordMapper;
import com.ziyao.harbor.usercenter.service.LoginRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录记录表 服务实现类
 * </p>
 *
 * @author zhangziyao
 */
@Service
@RequiredArgsConstructor
public class LoginRecordServiceImpl extends ServiceImpl<LoginRecordMapper, LoginRecord> implements LoginRecordService {

    private final LoginRecordMapper loginRecordMapper;

    @Override
    public Page<LoginRecord> page(Page<LoginRecord> page, LoginRecordDTO loginRecordDTO) {
        LambdaQueryWrapper<LoginRecord> wrapper = loginRecordDTO.initWrapper();
        // to do 2023/5/6 默认排序字段 sort/sorted(默认是为ASC)值越小、越往前
        return loginRecordMapper.selectPage(page, wrapper);
    }
}
