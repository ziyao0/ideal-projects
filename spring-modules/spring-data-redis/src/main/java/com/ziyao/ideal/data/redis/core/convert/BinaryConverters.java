package com.ziyao.ideal.data.redis.core.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.util.NumberUtils;
import org.springframework.util.ObjectUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

/**
 * @author ziyao zhang
 *
 */
public class BinaryConverters {

    /**
     * Use {@literal UTF-8} as default charset.
     */
    public static final Charset CHARSET = StandardCharsets.UTF_8;

    private BinaryConverters() {
    }

    static Collection<Converter<?, ?>> getConvertersToRegister() {

        List<Converter<?, ?>> converters = new ArrayList<>(12);

        converters.add(new BinaryConverters.StringToBytesConverter());
        converters.add(new BinaryConverters.BytesToStringConverter());
        converters.add(new BinaryConverters.NumberToBytesConverter());
//        converters.add(new BinaryConverters.BytesToNumberConverterFactory());
        converters.add(new BinaryConverters.EnumToBytesConverter());
//        converters.add(new BinaryConverters.BytesToEnumConverterFactory());
        converters.add(new BinaryConverters.BooleanToBytesConverter());
        converters.add(new BinaryConverters.BytesToBooleanConverter());
        converters.add(new BinaryConverters.DateToBytesConverter());
        converters.add(new BinaryConverters.BytesToDateConverter());
        converters.add(new BinaryConverters.UuidToBytesConverter());
        converters.add(new BinaryConverters.BytesToUuidConverter());

        return converters;
    }

    /**
     * @author Christoph Strobl
     */
    static class StringBasedConverter {

        byte[] fromString(String source) {
            return source.getBytes(CHARSET);
        }

        String toString(byte[] source) {
            return new String(source, CHARSET);
        }
    }

    /**
     * @author Christoph Strobl
     */
    @WritingConverter
    static class StringToBytesConverter extends BinaryConverters.StringBasedConverter implements Converter<String, byte[]> {

        @Override
        public byte[] convert(String source) {
            return fromString(source);
        }
    }

    /**
     * @author Christoph Strobl
     */
    @ReadingConverter
    static class BytesToStringConverter extends BinaryConverters.StringBasedConverter implements Converter<byte[], String> {

        @Override
        public String convert(byte[] source) {
            return toString(source);
        }

    }

    /**
     * @author Christoph Strobl
     */
    @WritingConverter
    static class NumberToBytesConverter extends BinaryConverters.StringBasedConverter implements Converter<Number, byte[]> {

        @Override
        public byte[] convert(Number source) {
            return fromString(source.toString());
        }
    }

    /**
     * @author Christoph Strobl
     * @author Mark Paluch
     */
    @WritingConverter
    static class EnumToBytesConverter extends BinaryConverters.StringBasedConverter implements Converter<Enum<?>, byte[]> {

        @Override
        public byte[] convert(Enum<?> source) {
            return fromString(source.name());
        }
    }

    /**
     * @author Christoph Strobl
     */
    @ReadingConverter
    static final class BytesToEnumConverterFactory implements ConverterFactory<byte[], Enum<?>> {

        @Override
        @SuppressWarnings({"unchecked", "rawtypes"})
        public <T extends Enum<?>> Converter<byte[], T> getConverter(Class<T> targetType) {

            Class<?> enumType = targetType;
            while (enumType != null && !enumType.isEnum()) {
                enumType = enumType.getSuperclass();
            }
            if (enumType == null) {
                throw new IllegalArgumentException("The target type " + targetType.getName() + " does not refer to an enum");
            }
            return new BinaryConverters.BytesToEnumConverterFactory.BytesToEnum(enumType);
        }

        /**
         * @author Christoph Strobl
         */
        private class BytesToEnum<T extends Enum<T>> extends BinaryConverters.StringBasedConverter implements Converter<byte[], T> {

            private final Class<T> enumType;

            public BytesToEnum(Class<T> enumType) {
                this.enumType = enumType;
            }

            @Override
            public T convert(byte[] source) {

                if (ObjectUtils.isEmpty(source)) {
                    return null;
                }

                return Enum.valueOf(this.enumType, toString(source).trim());
            }
        }
    }

    /**
     * @author Christoph Strobl
     */
    @ReadingConverter
    static class BytesToNumberConverterFactory implements ConverterFactory<byte[], Number> {

        @Override
        public <T extends Number> Converter<byte[], T> getConverter(Class<T> targetType) {
            return new BinaryConverters.BytesToNumberConverterFactory.BytesToNumberConverter<>(targetType);
        }

        private static final class BytesToNumberConverter<T extends Number> extends BinaryConverters.StringBasedConverter
                implements Converter<byte[], T> {

            private final Class<T> targetType;

            public BytesToNumberConverter(Class<T> targetType) {
                this.targetType = targetType;
            }

            @Override
            public T convert(byte[] source) {

                if (ObjectUtils.isEmpty(source)) {
                    return null;
                }

                return NumberUtils.parseNumber(toString(source), targetType);
            }
        }
    }

    /**
     * @author Christoph Strobl
     */
    @WritingConverter
    static class BooleanToBytesConverter extends BinaryConverters.StringBasedConverter implements Converter<Boolean, byte[]> {

        byte[] _true = fromString("1");
        byte[] _false = fromString("0");

        @Override
        public byte[] convert(Boolean source) {
            return source.booleanValue() ? _true : _false;
        }
    }

    /**
     * @author Christoph Strobl
     */
    @ReadingConverter
    static class BytesToBooleanConverter extends BinaryConverters.StringBasedConverter implements Converter<byte[], Boolean> {

        @Override
        public Boolean convert(byte[] source) {

            if (ObjectUtils.isEmpty(source)) {
                return null;
            }

            String value = toString(source);
            return ("1".equals(value) || "true".equalsIgnoreCase(value)) ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    /**
     * @author Christoph Strobl
     */
    @WritingConverter
    static class DateToBytesConverter extends BinaryConverters.StringBasedConverter implements Converter<Date, byte[]> {

        @Override
        public byte[] convert(Date source) {
            return fromString(Long.toString(source.getTime()));
        }
    }

    /**
     * @author Christoph Strobl
     */
    @ReadingConverter
    static class BytesToDateConverter extends BinaryConverters.StringBasedConverter implements Converter<byte[], Date> {

        @Override
        public Date convert(byte[] source) {

            if (ObjectUtils.isEmpty(source)) {
                return null;
            }

            String value = toString(source);
            try {
                return new Date(NumberUtils.parseNumber(value, Long.class));
            } catch (NumberFormatException ignore) {
            }

            try {
                return DateFormat.getInstance().parse(value);
            } catch (ParseException ignore) {
            }

            throw new IllegalArgumentException(String.format("Cannot parse date out of %s", Arrays.toString(source)));
        }
    }

    /**
     * @author Christoph Strobl
     */
    @WritingConverter
    static class UuidToBytesConverter extends BinaryConverters.StringBasedConverter implements Converter<UUID, byte[]> {

        @Override
        public byte[] convert(UUID source) {
            return fromString(source.toString());
        }
    }

    /**
     * @author Christoph Strobl
     */
    @ReadingConverter
    static class BytesToUuidConverter extends BinaryConverters.StringBasedConverter implements Converter<byte[], UUID> {

        @Override
        public UUID convert(byte[] source) {

            if (ObjectUtils.isEmpty(source)) {
                return null;
            }

            return UUID.fromString(toString(source));
        }
    }
}
