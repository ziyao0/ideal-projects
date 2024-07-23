package com.ziyao.eis.core;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public enum Formatter {

    /**
     * 秒
     */
    SECONDS("yyyy-MM-dd HH:mm:ss"),
    /**
     * 分钟
     */
    MINUTES("yyyy-MM-dd HH:mm"),
    /**
     * 小时
     */
    HOURS("yyyy-MM-dd HH"),
    /**
     * 天
     */
    DAY("yyyy-MM-dd"),
    /**
     * 月
     */
    MONTH("yyyy-MM"),
    /**
     * 年
     */
    YEAR("yyyy"),
    ;

    private final String pattern;

    Formatter(String pattern) {
        this.pattern = pattern;
    }

    public static void main(String[] args) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MONTH.getPattern());

        System.out.println(LocalDateTime.now().format(dateTimeFormatter));

    }
}
