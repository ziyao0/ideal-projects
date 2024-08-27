package com.ziyao.ideal.generator.fill;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.IFill;
import com.ziyao.ideal.generator.mybatisplus.FieldFill;

/**
 * 字段填充
 */
public class Column implements IFill {

    private final String columnName;

    private final FieldFill fieldFill;

    public Column(@NonNull String columnName, @NonNull FieldFill fieldFill) {
        this.columnName = columnName;
        this.fieldFill = fieldFill;
    }

    public Column(String columnName) {
        this.columnName = columnName;
        this.fieldFill = FieldFill.DEFAULT;
    }

    @Override
    public @NonNull String getName() {
        return this.columnName;
    }

    @Override
    public @NonNull FieldFill getFieldFill() {
        return this.fieldFill;
    }
}
