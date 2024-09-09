package com.ziyao.ideal.convert;

import org.springframework.core.convert.converter.Converter;

import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.ziyao.ideal.convert.BinaryConverters.StringBasedConverter;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public abstract class Jsr310Converters {

    /**
     * Returns the {@link Converter Converters} to be registered.
     *
     * @return the {@link Converter Converters} to be registered.
     */
    public static Collection<Converter<?, ?>> getConvertersToRegister() {

        List<Converter<?, ?>> converters = new ArrayList<>(20);

        converters.add(new LocalDateTimeToBytesConverter());
        converters.add(new BytesToLocalDateTimeConverter());
        converters.add(new LocalDateToBytesConverter());
        converters.add(new BytesToLocalDateConverter());
        converters.add(new LocalTimeToBytesConverter());
        converters.add(new BytesToLocalTimeConverter());
        converters.add(new ZonedDateTimeToBytesConverter());
        converters.add(new BytesToZonedDateTimeConverter());
        converters.add(new InstantToBytesConverter());
        converters.add(new BytesToInstantConverter());
        converters.add(new ZoneIdToBytesConverter());
        converters.add(new BytesToZoneIdConverter());
        converters.add(new PeriodToBytesConverter());
        converters.add(new BytesToPeriodConverter());
        converters.add(new DurationToBytesConverter());
        converters.add(new BytesToDurationConverter());
        converters.add(new OffsetDateTimeToBytesConverter());
        converters.add(new BytesToOffsetDateTimeConverter());
        converters.add(new OffsetTimeToBytesConverter());
        converters.add(new BytesToOffsetTimeConverter());

        return converters;
    }

    public static boolean supports(Class<?> type) {

        return Arrays.<Class<?>>asList(LocalDateTime.class, LocalDate.class, LocalTime.class, Instant.class,
                        ZonedDateTime.class, ZoneId.class, Period.class, Duration.class, OffsetDateTime.class, OffsetTime.class)
                .contains(type);
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class LocalDateTimeToBytesConverter extends StringBasedConverter implements Converter<LocalDateTime, byte[]> {

        @Override
        public byte[] convert(LocalDateTime source) {
            return fromString(source.toString());
        }
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class BytesToLocalDateTimeConverter extends StringBasedConverter implements Converter<byte[], LocalDateTime> {

        @Override
        public LocalDateTime convert(byte[] source) {
            return LocalDateTime.parse(toString(source));
        }
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class LocalDateToBytesConverter extends StringBasedConverter implements Converter<LocalDate, byte[]> {

        @Override
        public byte[] convert(LocalDate source) {
            return fromString(source.toString());
        }
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class BytesToLocalDateConverter extends StringBasedConverter implements Converter<byte[], LocalDate> {

        @Override
        public LocalDate convert(byte[] source) {
            return LocalDate.parse(toString(source));
        }
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class LocalTimeToBytesConverter extends StringBasedConverter implements Converter<LocalTime, byte[]> {

        @Override
        public byte[] convert(LocalTime source) {
            return fromString(source.toString());
        }
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class BytesToLocalTimeConverter extends StringBasedConverter implements Converter<byte[], LocalTime> {

        @Override
        public LocalTime convert(byte[] source) {
            return LocalTime.parse(toString(source));
        }
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class ZonedDateTimeToBytesConverter extends StringBasedConverter implements Converter<ZonedDateTime, byte[]> {

        @Override
        public byte[] convert(ZonedDateTime source) {
            return fromString(source.toString());
        }
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class BytesToZonedDateTimeConverter extends StringBasedConverter implements Converter<byte[], ZonedDateTime> {

        @Override
        public ZonedDateTime convert(byte[] source) {
            return ZonedDateTime.parse(toString(source));
        }
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class InstantToBytesConverter extends StringBasedConverter implements Converter<Instant, byte[]> {

        @Override
        public byte[] convert(Instant source) {
            return fromString(source.toString());
        }
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class BytesToInstantConverter extends StringBasedConverter implements Converter<byte[], Instant> {

        @Override
        public Instant convert(byte[] source) {
            return Instant.parse(toString(source));
        }
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class ZoneIdToBytesConverter extends StringBasedConverter implements Converter<ZoneId, byte[]> {

        @Override
        public byte[] convert(ZoneId source) {
            return fromString(source.toString());
        }
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class BytesToZoneIdConverter extends StringBasedConverter implements Converter<byte[], ZoneId> {

        @Override
        public ZoneId convert(byte[] source) {
            return ZoneId.of(toString(source));
        }
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class PeriodToBytesConverter extends StringBasedConverter implements Converter<Period, byte[]> {

        @Override
        public byte[] convert(Period source) {
            return fromString(source.toString());
        }
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class BytesToPeriodConverter extends StringBasedConverter implements Converter<byte[], Period> {

        @Override
        public Period convert(byte[] source) {
            return Period.parse(toString(source));
        }
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class DurationToBytesConverter extends StringBasedConverter implements Converter<Duration, byte[]> {

        @Override
        public byte[] convert(Duration source) {
            return fromString(source.toString());
        }
    }

    /**
     * @author Mark Paluch
     * @since 1.7
     */
    static class BytesToDurationConverter extends StringBasedConverter implements Converter<byte[], Duration> {

        @Override
        public Duration convert(byte[] source) {
            return Duration.parse(toString(source));
        }
    }

    /**
     * @author John Blum
     * @since 3.1.3
     */
    static class OffsetDateTimeToBytesConverter extends StringBasedConverter
            implements Converter<OffsetDateTime, byte[]> {

        @Override
        public byte[] convert(OffsetDateTime source) {
            return fromString(source.toString());
        }
    }

    /**
     * @author John Blum
     * @since 3.1.3
     */
    static class BytesToOffsetDateTimeConverter extends StringBasedConverter
            implements Converter<byte[], OffsetDateTime> {

        @Override
        public OffsetDateTime convert(byte[] source) {
            return OffsetDateTime.parse(toString(source));
        }
    }

    /**
     * @author John Blum
     * @since 3.1.3
     */
    static class OffsetTimeToBytesConverter extends StringBasedConverter implements Converter<OffsetTime, byte[]> {

        @Override
        public byte[] convert(OffsetTime source) {
            return fromString(source.toString());
        }
    }

    /**
     * @author John Blum
     * @since 3.1.3
     */
    static class BytesToOffsetTimeConverter extends StringBasedConverter implements Converter<byte[], OffsetTime> {

        @Override
        public OffsetTime convert(byte[] source) {
            return OffsetTime.parse(toString(source));
        }
    }
}
