package com.ziyao.ideal.config.core;

import com.ziyao.ideal.core.Strings;
import lombok.Getter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public enum ConfigValueType {

    STRING(String.class),
    INTEGER(Integer.class),
    LONG(Long.class),
    DOUBLE(Double.class),
    BOOLEAN(Boolean.class),
    DATE(Date.class),
    NUMBER(Number.class),
    OBJECT(Object.class), ARRAY(List.class);


    private final Class<?> classType;

    ConfigValueType(Class<?> classType) {
        this.classType = classType;
    }

    private static final Map<String, ConfigValueType> map;

    static {
        map = Arrays.stream(values()).collect(Collectors.toMap(ConfigValueType::name, Function.identity()));
    }

    public static ConfigValueType of(String name) {
        ConfigValueType configValueType = map.get(name);
        return configValueType == null ? STRING : configValueType;
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(String value) throws ParseException {

        return switch (this) {
            case STRING, OBJECT -> (T) value;
            case INTEGER -> (T) Integer.valueOf(value);
            case LONG -> (T) Long.valueOf(value);
            case DOUBLE -> (T) Double.valueOf(value);
            case BOOLEAN -> (T) Boolean.valueOf(value);
            case DATE -> (T) DateFormat.getDateInstance().parse(value);
            case ARRAY -> (T) Strings.commaDelimitedListToList(value);
            default -> (T) this.classType.cast(value);
        };
    }
}
