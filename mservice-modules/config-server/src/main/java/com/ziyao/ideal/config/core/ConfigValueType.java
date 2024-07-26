package com.ziyao.ideal.config.core;

import com.ziyao.ideal.core.Strings;
import lombok.Getter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public enum ConfigValueType {

    STRING(String.class), INTEGER(Integer.class),
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

    @SuppressWarnings("unchecked")
    public <T> T getInstance(String value) throws ParseException {

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

    public static void main(String[] args) throws ParseException {
        Boolean aTrue = ConfigValueType.BOOLEAN.getInstance("true");
        System.out.println(aTrue);

        List<String> instance = ConfigValueType.ARRAY.getInstance("a,b,c");
        System.out.println(instance);
    }
}
