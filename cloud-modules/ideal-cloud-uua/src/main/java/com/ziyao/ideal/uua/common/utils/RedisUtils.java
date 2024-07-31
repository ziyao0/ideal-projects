package com.ziyao.ideal.uua.common.utils;

import com.ziyao.ideal.core.Dates;

import java.util.Locale;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public abstract class RedisUtils {


    public static class Metrics {

        private static final String MONITORING_METRICS = "sys:monitor:metrics:login:";

        private static final String TOTAL = "total";
        private static final String ONLINE = "online";


        public static String getTotalKey() {
            return MONITORING_METRICS + TOTAL;
        }

        public static String getOnlineKey() {
            return MONITORING_METRICS + ONLINE;
        }

        public static String getYearKey() {
            return MONITORING_METRICS + Dates.formatOfYear();
        }

        public static String getMonthKey() {
            return MONITORING_METRICS + Dates.formatOfMonth();
        }

        public static String getDayKey() {
            return MONITORING_METRICS + Dates.formatOfDay();
        }

    }

    public static class Uaa {
        private static final String PASSWD_ERROR_ = "sys:uaa:password_error:";

        public static String getPasswordErrorTimesKey(String username) {
            return PASSWD_ERROR_ + username.toLowerCase(Locale.ROOT);
        }
    }
}
