package com.ziyao.ideal.generator.fill;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.IFill;
import com.ziyao.ideal.generator.mybatisplus.FieldFill;

/**
 * 属性填充
 */
public class Property implements IFill {

    private final String propertyName;

    private final FieldFill fieldFill;

    public Property(@NonNull String propertyName, @NonNull FieldFill fieldFill) {
        this.propertyName = propertyName;
        this.fieldFill = fieldFill;
    }

    public Property(@NonNull String propertyName) {
        this.propertyName = propertyName;
        this.fieldFill = FieldFill.DEFAULT;
    }

    @Override
    public @NonNull String getName() {
        return this.propertyName;
    }

    @Override
    public @NonNull FieldFill getFieldFill() {
        return this.fieldFill;
    }
}
