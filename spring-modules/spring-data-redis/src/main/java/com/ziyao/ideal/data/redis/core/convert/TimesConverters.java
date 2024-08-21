package com.ziyao.ideal.data.redis.core.convert;

import com.google.common.collect.Lists;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class TimesConverters {

    private TimesConverters() {
    }


    static Collection<Converter<?, ?>> getConvertersToRegister() {

        return Lists.newArrayList(
                new LocalDateTimeToStringConverter(),
                new LocalDateToStringConverter(),
                new DateToStringConverter(),
                new InstantToStringConverter(),
                new LocalTimeToStringConverter()
        );

    }


    static class LocalDateTimeToStringConverter implements Converter<LocalDateTime, String> {

        @Override
        public String convert(@Nullable LocalDateTime source) {
            if (null == source) {
                return null;
            }

            return source.toString();
        }
    }

    static class LocalDateToStringConverter implements Converter<LocalDate, String> {

        @Override
        public String convert(@Nullable LocalDate source) {
            if (null == source) {
                return null;
            }

            return source.toString();
        }
    }

    static class DateToStringConverter implements Converter<Date, String> {

        @Override
        public String convert(@Nullable Date source) {
            if (null == source) {
                return null;
            }

            return source.toString();
        }
    }

    static class InstantToStringConverter implements Converter<Instant, String> {

        @Override
        public String convert(@Nullable Instant source) {
            if (null == source) {
                return null;
            }

            return source.toString();
        }
    }

    static class LocalTimeToStringConverter implements Converter<LocalTime, String> {

        @Override
        public String convert(@Nullable LocalTime source) {
            if (null == source) {
                return null;
            }

            return source.toString();
        }
    }
}
