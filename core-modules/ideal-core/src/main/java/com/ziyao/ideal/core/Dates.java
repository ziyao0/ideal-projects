package com.ziyao.ideal.core;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author zhangziyao
 */
public abstract class Dates {


    private Dates() {
    }

    public static String formatOfDay() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(Pattern.DAY.value()));
    }

    public static String formatOfMonth() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(Pattern.MONTH.value()));
    }

    public static String formatOfYear() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(Pattern.YEAR.value()));
    }

    /**
     * 判断是是否过期 误差为1分钟 一分钟之内都算过期
     *
     * @param expiresAt 时间类型
     */
    public static boolean isExpired(Date expiresAt) {
        final Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, 2);
        Date date = instance.getTime();
        return expiresAt.compareTo(date) <= 0;
    }

    /**
     * 判断是是否过期 误差为1分钟 一分钟之内都算过期
     *
     * @param expiresAt 时间类型
     */
    public static boolean isExpired(Date expiresAt, int amount) {
        final Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, amount);
        Date date = instance.getTime();
        return expiresAt.compareTo(date) <= 0;
    }

    /**
     * 跳跃到指定的时间之后 （单位：分钟）
     *
     * @param minute 分钟数
     */
    public static Date skip(int minute) {
        final Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, minute);
        return instance.getTime();
    }

    public static Instant toInstant(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    public static LocalDateTime toLocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static TimeZone getDefaultTimeZone() {
        return TimeZone.getTimeZone("GMT+8");
    }

    public static Date parseDate(String dateStr, final Pattern pattern) {
        LocalDateTime localDateTime = parse(dateStr, pattern);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime parse(String dateStr) {
        return parse(dateStr, Pattern.SECOND);
    }

    public static LocalDateTime parse(String dateStr, final Pattern pattern) {
        return parse(dateStr, pattern.value);
    }

    public static LocalDateTime parse(String dateStr, final String pattern) {
        return parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parse(String dateStr, final DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateStr, formatter);
    }


    public static String format(LocalDateTime localDateTime) {
        return format(localDateTime, Pattern.SECOND.value);
    }

    public static String format(TemporalAccessor temporal, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(temporal);
    }

    public static String format(TemporalAccessor temporal, Pattern pattern) {
        return DateTimeFormatter.ofPattern(pattern.value()).format(temporal);
    }

    public static String format(Date date, Pattern pattern) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return DateTimeFormatter.ofPattern(pattern.value()).format(localDateTime);
    }

    public enum Pattern {

        YEAR("yyyy"),
        MONTH("yyyy-MM"),
        DAY("yyyy-MM-dd"),
        TIME("HH:mm:ss"),
        HOUR("yyyy-MM-dd HH"),
        MINUTE("yyyy-MM-dd HH:mm"),
        SECOND("yyyy-MM-dd HH:mm:ss"),
        NANOSECOND("yyyy-MM-dd HH:mm:ss.SSS"),
        ;

        private final String value;

        Pattern(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
