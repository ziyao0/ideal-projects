package com.ziyao.ideal.generator.metadata;

import com.ziyao.ideal.core.Collections;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class Table {

    private final String name;
    private final String comment;
    private final String type;
    private List<Column> columns;

    public Table(String name, String comment, String type) {
        this.name = name;
        this.comment = comment;
        this.type = type;
    }

    public boolean isView() {
        return "VIEW".equals(type);
    }

    public void setColumns(List<Column> columns) {
        if (Collections.isEmpty(columns)) {
            columns = new ArrayList<>();
        }
        this.columns = columns;
    }

    public static Table of(String name, String comment, String type) {
        return new Table(name, comment, type);
    }
}
