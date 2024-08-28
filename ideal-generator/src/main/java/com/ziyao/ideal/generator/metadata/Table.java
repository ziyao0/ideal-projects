package com.ziyao.ideal.generator.metadata;

import java.util.List;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public record Table(String name, String comment, List<Column> columns) {

    public static Table of(String name, List<Column> columns) {
        return of(name, null, columns);
    }

    public static Table of(String name, String comment, List<Column> columns) {
        return new Table(name, comment, columns);
    }
}
