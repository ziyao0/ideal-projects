package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.dto.AuthorizationRecordDTO;
import com.ziyao.ideal.uaa.domain.entity.AuthorizationRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 授权记录表 服务类
 * </p>
 *
 * @author ziyao
 */
public interface AuthorizationRecordService {

    /**
     * 保存 授权记录表
     *
     * @param authorizationRecordDTO 待存储对象
     */
    void save(AuthorizationRecordDTO authorizationRecordDTO);

    /**
     * 通过主键修改 授权记录表
     *
     * @param authorizationRecordDTO 待修改对象
     */
    void updateById(AuthorizationRecordDTO authorizationRecordDTO);

    /**
     * 通过主键删除 授权记录表
     *
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 分页查询 授权记录表
     *
     * @param authorizationRecordDTO 查询对象
     * @param pageable               分页对象
     * @return 返回分页对象
     */
    Page<AuthorizationRecord> page(AuthorizationRecordDTO authorizationRecordDTO, Pageable pageable);
}
