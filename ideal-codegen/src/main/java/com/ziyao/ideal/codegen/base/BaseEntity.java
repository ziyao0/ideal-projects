package com.ziyao.ideal.codegen.base;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ziyao zhang
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 创建人id
     */
    @TableField("CREATED_BY")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @TableField("CREATED_AT")
    private LocalDateTime createdAt;

    /**
     * 修改人id
     */
    @TableField("MODIFIED_BY")
    private Integer modifiedBy;

    /**
     * 修改时间
     */
    @TableField("MODIFIED_AT")
    private LocalDateTime modifiedAt;

}
