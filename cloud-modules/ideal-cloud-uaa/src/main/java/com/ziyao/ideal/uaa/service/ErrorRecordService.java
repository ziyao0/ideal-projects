package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.dto.ErrorRecordDTO;
import com.ziyao.ideal.uaa.domain.entity.ErrorRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 异常信息记录表 服务类
 * </p>
 *
 * @author ziyao
 */
public interface ErrorRecordService {

    /**
     * 保存 异常信息记录表
     *
     * @param errorRecordDTO 待存储对象
     */
    void save(ErrorRecordDTO errorRecordDTO);

    /**
     * 通过主键修改 异常信息记录表
     *
     * @param errorRecordDTO 待修改对象
     */
    void updateById(ErrorRecordDTO errorRecordDTO);

    /**
     * 通过主键删除 异常信息记录表
     *
     * @param id 主键id
     */
    void deleteById(Long id);

    /**
     * 分页查询 异常信息记录表
     *
     * @param errorRecordDTO 查询对象
     * @param pageable       分页对象
     * @return 返回分页对象
     */
    Page<ErrorRecord> page(ErrorRecordDTO errorRecordDTO, Pageable pageable);
}
