package com.ziyao.ideal.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangziyao
 */
public abstract class DateUtils {


    private DateUtils() {
    }

    /**
     * 获取当前时间字符串，单位是天
     *
     * @return 返回当前日期
     */
    public static String formatOfDay() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(Pattern.DAY.value()));
    }

    /**
     * 获取当前月份字符串
     *
     * @return 获取当前月份
     */
    public static String formatOfMonth() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(Pattern.MONTH.value()));
    }

    /**
     * 获取当前年份
     *
     * @return 返回当前时间的年份
     */
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


    /**
     * 获取前一天日期
     *
     * @return 返回时间字符串
     */
    public static String getBeforeOneDayTime() {
        final Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, -1);
        return instance.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern(Pattern.DAY.value()));
    }

    /**
     * 计算两个时间之间的天数
     * <p>
     * 如果开始时间比结束时间大则返回0天
     *
     * @param start 开始时间
     * @param end   就结束时间
     * @return 返回天数
     */
    public static long getBetweenDays(String start, String end) {
        long days = Duration.between(
                LocalDateTime.parse(start, DateTimeFormatter.ofPattern(Pattern.SECOND.value)),
                LocalDateTime.parse(end, DateTimeFormatter.ofPattern(Pattern.SECOND.value))).toDays();
        if (days < 0) {
            return 0;
        }
        return days;
    }

    /**
     * 计算两个时间之间的秒值
     *
     * @param start 开始时间
     * @param end   就结束时间
     * @return 返回秒
     */
    public static long getBetweenSeconds(String start, String end) {
        return Duration.between(
                LocalDateTime.parse(start, DateTimeFormatter.ofPattern(Pattern.SECOND.value)),
                LocalDateTime.parse(end, DateTimeFormatter.ofPattern(Pattern.SECOND.value))).getSeconds();
    }

    /**
     * 计算两个时间之间的间隔数量
     * <p>
     * 如果开始时间比结束时间大则返回0
     * {@link TimeUnit}间隔单位
     *
     * @param start   开始时间
     * @param end     就结束时间
     * @param pattern 时间单位
     * @return 返回天数
     */
    public static long getBetweenOfType(String start, String end, Pattern pattern) {
        Duration duration = Duration.between(
                LocalDateTime.parse(start, DateTimeFormatter.ofPattern(Pattern.SECOND.value)),
                LocalDateTime.parse(end, DateTimeFormatter.ofPattern(Pattern.SECOND.value)));
        return switch (pattern) {
            case DAY -> duration.toDays();
            case HOUR -> duration.toHours();
            case MINUTE -> duration.toMinutes();
            default -> duration.toMillis();
        };
    }

    /**
     * date convert localDateTime
     *
     * @param date date
     * @return <code>LocalDateTime</code>
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * 判断当前时间是否在指定时间段内
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return <code>true</code> 为在指定时间段内
     */
    public static boolean isBetween(Date start, Date end) {
        if (Objects.isNull(start) || Objects.isNull(end)) {
            return false;
        }
        final LocalDateTime now = LocalDateTime.now();
        return now.isBefore(toLocalDateTime(end)) && now.isAfter(toLocalDateTime(start));
    }

    /**
     * 格式化时间
     *
     * @param dateTime LocalDateTime对象
     * @param pattern  要格式化的字符串
     * @return 返回日期字符串
     */
    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern(Strings.isEmpty(pattern) ? Pattern.SECOND.value : pattern));
    }

    /**
     * date转字符串
     *
     * @param date 日期
     * @return 返回日期字符串
     */
    public static String format(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return formatDateTime(instant.atZone(zoneId).toLocalDateTime());
    }


    /**
     * 格式化时间
     *
     * @param dateTime LocalDateTime对象
     * @return 2020-05-01 00:00:00
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return formatDateTime(dateTime, Pattern.SECOND.value);
    }

    /**
     * 获取当天的起始时间
     *
     * @return 如：2020-05-23 00:00:00
     */
    public static String getStartTime() {
        return formatDateTime(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0));
    }

    /**
     * 获取当天的结束时间
     *
     * @return 如：2020-05-23 23:59:59
     */
    public static String getEndTime() {
        return formatDateTime(LocalDateTime.now().withHour(23).withMinute(59).withSecond(59));
    }

    /**
     * 获取当前时间的前n月的时间
     *
     * @return 返回日期字符串
     */
    public static String getBeforeNMonthTime(Date date, Integer month) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusMonths((-month)).format(DateTimeFormatter.ofPattern(Pattern.SECOND.value()));
    }

    /**
     * 获取当前时间的前一天时间
     *
     * @return 返回日期字符串
     */
    public static String getYesterdayTime() {
        return LocalDateTime.now().plusDays(-1).format(DateTimeFormatter.ofPattern(Pattern.DAY.value()));
    }

    /**
     * 获取传入时间后n天的时间
     *
     * @return 返回日期字符串
     */
    public static String getAfterNTime(String date, Integer day) {
        return parse(date).plusDays(day).format(DateTimeFormatter.ofPattern(Pattern.SECOND.value()));
    }

    /**
     * 获取当前时间
     * 返回格式 {@link TimeUnit#SECONDS#}
     *
     * @return 返回当前时间
     */
    public static String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(Pattern.SECOND.value()));
    }

    /**
     * 遍历两个时间的天
     *
     * @param temporal1InclusiveOfString 开始时间
     * @param temporal2ExclusiveOfString 结束时间
     * @return 返回间隔日期的集合
     */
    public static List<String> iterateTimeOfDays(String temporal1InclusiveOfString,
                                                 String temporal2ExclusiveOfString) {
        LocalDateTime temporal1Inclusive = parse(temporal1InclusiveOfString);
        LocalDateTime temporal2Exclusive = parse(temporal2ExclusiveOfString);
        return iterateTime(temporal1Inclusive, temporal2Exclusive, ChronoUnit.DAYS, 1, Pattern.DAY.value);
    }

    /**
     * 遍历两个时间的天
     *
     * @param temporal1InclusiveOfString 开始时间
     * @param temporal2ExclusiveOfString 结束时间
     * @return 返回间隔日期的集合
     */
    public static List<String> iterateTimeOfDays(String temporal1InclusiveOfString,
                                                 String temporal2ExclusiveOfString,
                                                 String pattern) {
        LocalDateTime temporal1Inclusive = parse(temporal1InclusiveOfString);
        LocalDateTime temporal2Exclusive = parse(temporal2ExclusiveOfString);
        return iterateTime(temporal1Inclusive, temporal2Exclusive, ChronoUnit.DAYS, 1, pattern);
    }

    /**
     * 遍历两个时间的字符串
     *
     * @param temporal1InclusiveOfString 开始时间
     * @param temporal2ExclusiveOfString 结束时间
     * @param chronoUnit                 以{@code ChronoUnit} 为转换单位
     * @param amountToAdd                添加量->间隔
     * @param pattern                    输出日期的格式
     * @return 返回间隔日期的集合
     */
    public static List<String> iterateTime(String temporal1InclusiveOfString,
                                           String temporal2ExclusiveOfString,
                                           ChronoUnit chronoUnit, long amountToAdd, String pattern) {
        LocalDateTime temporal1Inclusive = parse(temporal1InclusiveOfString);
        LocalDateTime temporal2Exclusive = parse(temporal2ExclusiveOfString);
        return iterateTime(temporal1Inclusive, temporal2Exclusive, chronoUnit, amountToAdd, pattern);
    }

    /**
     * 遍历两个时间的字符串
     *
     * @param temporal1Inclusive 开始时间
     * @param temporal2Exclusive 结束时间
     * @param chronoUnit         以{@code ChronoUnit} 为转换单位
     * @param amountToAdd        添加量->间隔
     * @param pattern            输出日期的格式
     * @return 返回间隔日期的集合
     */
    public static List<String> iterateTime(Temporal temporal1Inclusive,
                                           Temporal temporal2Exclusive,
                                           ChronoUnit chronoUnit, long amountToAdd, String pattern) {

        return Stream
                .iterate(temporal1Inclusive, temporal -> temporal.plus(amountToAdd, chronoUnit))
                .limit((chronoUnit.between(temporal1Inclusive, temporal2Exclusive) + amountToAdd) / amountToAdd)
                .map(temporal -> DateTimeFormatter.ofPattern(pattern).format(temporal)).collect(Collectors.toList());
    }

    /**
     * date字符串转时间戳
     *
     * @param date 日期字符串
     * @return 返回毫秒级时间戳
     */
    public static long parseTimestampAsMill(String date) {
        return parse(date).toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 日期转换 Tue May 25 07:00:00 CST 2021 TO yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr 源参数
     * @return 返回默秒参数
     */
    public static String parseFromUS(String dateStr) {
        return formatDateTime(ZonedDateTime
                .parse(dateStr, DateTimeFormatter.ofPattern(Pattern.DATE_TIME_US.value).withLocale(Locale.US)).toLocalDateTime());
    }


    /**
     * 时间戳转换为日期
     */
    public static String timestampConversion(Long timesTamp) {
        Date date = new Date(timesTamp);
        DateFormat dateFormat = new SimpleDateFormat(Pattern.SECOND.value);
        return dateFormat.format(date);
    }

    /**
     * localDateTime to instant
     *
     * @param localDateTime 传入日期
     * @return instant
     */

    public static Instant toInstant(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    /**
     * instant to localDateTime
     *
     * @param instant instant
     * @return 返回localDateTime
     */
    public static LocalDateTime toLocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * 获取当前默认时区
     *
     * @return {@link TimeZone}
     */
    public static TimeZone getDefaultTimeZone() {
        return TimeZone.getTimeZone("GMT+8");
    }

    /**
     * string date to date
     *
     * @param dateStr 字符串日期
     * @param pattern 格式化日期规则
     * @return 返回Date
     */
    public static Date parseDate(String dateStr, final Pattern pattern) {
        LocalDateTime localDateTime = parse(dateStr, pattern);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * string date to localDateTime
     *
     * @param dateStr 字符串日期
     * @return 返回localDateTime
     * @see Pattern#SECOND
     */
    public static LocalDateTime parse(String dateStr) {
        return parse(dateStr, Pattern.SECOND);
    }

    /**
     * string date to localDateTime
     *
     * @param dateStr 字符串日期
     * @param pattern 格式化日期规则
     * @return 返回localDateTime
     */
    public static LocalDateTime parse(String dateStr, final Pattern pattern) {
        return parse(dateStr, pattern.value);
    }

    /**
     * string date to localDateTime
     *
     * @param dateStr 字符串日期
     * @param pattern 格式化日期规则
     * @return 返回localDateTime
     */
    public static LocalDateTime parse(String dateStr, final String pattern) {
        return parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * string date to localDateTime
     *
     * @param dateStr   字符串日期
     * @param formatter 格式化日期规则
     * @return 返回localDateTime
     */
    public static LocalDateTime parse(String dateStr, final DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateStr, formatter);
    }

    /**
     * localDateTime to string
     *
     * @param localDateTime 传入日期
     * @return 返回字符串的日期
     * @see Pattern#SECOND
     */
    public static String format(LocalDateTime localDateTime) {
        return format(localDateTime, Pattern.SECOND.value);
    }

    /**
     * TemporalAccessor to string
     *
     * @param temporal 传入日期
     * @param pattern  转换格式
     * @return 字符串日期
     */
    public static String format(TemporalAccessor temporal, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(temporal);
    }

    /**
     * TemporalAccessor to string
     *
     * @param temporal 传入日期
     * @param pattern  转换格式
     * @return 字符串日期
     */
    public static String format(TemporalAccessor temporal, Pattern pattern) {
        return DateTimeFormatter.ofPattern(pattern.value()).format(temporal);
    }

    /**
     * TemporalAccessor to string
     *
     * @param date    传入日期
     * @param pattern 转换格式
     * @return 字符串日期
     */
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
        DATE_TIME_US("EEE MMM dd HH:mm:ss zzz yyyy");

        private final String value;

        Pattern(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
