package com.ziyao.ideal.generator.core.metadata;

import com.ziyao.ideal.generator.core.DataType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Data
@Builder
@ToString
public class Column {

    private boolean primary;
    private boolean autoIncrement;
    private String name;
    private int length;
    private boolean nullable;
    private String defaultValue;
    private int scale;
    private DataType dataType;
    private String comment;
    private boolean generatedColumn;
    private String typeName;

}
