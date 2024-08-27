package com.ziyao.ideal.generator;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.mybatisplus.FieldFill;

/**
 * 填充接口
 */
public interface IFill {

    @NonNull
    String getName();

    @NonNull
    FieldFill getFieldFill();

}
