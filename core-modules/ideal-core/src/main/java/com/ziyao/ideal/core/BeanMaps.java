package com.ziyao.ideal.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ziyao zhang
 */
public abstract class BeanMaps {

    private BeanMaps() {
    }

    public static Map<String, Object> tomap(Object object) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (object != null) {
                Class<?> clazz = object.getClass();
                for (Field field : clazz.getDeclaredFields()) {
                    makeAccessible(field);
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object value = field.get(object);
                    map.put(fieldName, value);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @SuppressWarnings("deprecation")
    public static void makeAccessible(Field field) {
        if ((!Modifier.isPublic(field.getModifiers()) ||
                !Modifier.isPublic(field.getDeclaringClass().getModifiers()) ||
                Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
            field.setAccessible(true);
        }
    }

}
