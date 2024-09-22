package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.dto.LoginRecordDTO;
import com.ziyao.ideal.uaa.domain.entity.LoginRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 登录记录表 服务类
 * </p>
 *
 * @author ziyao
 */
public interface LoginRecordService {

    /**
     * 保存 登录记录表
     *
     * @param loginRecordDTO 待存储对象
     */
    void save(LoginRecordDTO loginRecordDTO);

    /**
     * 通过主键修改 登录记录表
     *
     * @param loginRecordDTO 待修改对象
     */
    void updateById(LoginRecordDTO loginRecordDTO);

    /**
     * 通过主键删除 登录记录表
     *
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 分页查询 登录记录表
     *
     * @param loginRecordDTO 查询对象
     * @param pageable       分页对象
     * @return 返回分页对象
     */
    Page<LoginRecord> page(LoginRecordDTO loginRecordDTO, Pageable pageable);
}
